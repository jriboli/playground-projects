
#from celery import Celery
from backend.tasks.celery_app import celery
import requests
import json
import os
from backend.api.models.trial import Trial
from backend.api.utils.db import get_db_session

#app = Celery("tasks", broker=os.getenv("CELERY_BROKER_URL", "redis://localhost:6379/0"))

CLINICAL_TRIALS_API = "https://clinicaltrials.gov/api/query/study_fields?expr={}&fields={}&fmt=json"

# Fields to fetch from ClinicalTrials.gov
FIELDS = "NCTId,Condition,Phase,EnrollmentCount,BriefTitle,StartDate,LocationCountry"

@celery.task
def fetch_trials(condition=""):
    """
    Fetches new clinical trials from ClinicalTrials.gov and stores them in the database.
    :param condition: Optional condition to filter trials.
    """
    url = CLINICAL_TRIALS_API.format(condition, FIELDS)
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        trials = data["StudyFieldsResponse"]["StudyFields"]

        session = get_db_session()
        for trial in trials:
            existing = session.query(Trial).filter_by(nct_id=trial["NCTId"][0]).first()
            if not existing:
                new_trial = Trial(
                    nct_id=trial["NCTId"][0],
                    title=trial["BriefTitle"][0] if trial["BriefTitle"] else "No Title",
                    condition=trial["Condition"][0] if trial["Condition"] else "Unknown",
                    phase=trial["Phase"][0] if trial["Phase"] else "Not Specified",
                    enrollment=int(trial["EnrollmentCount"][0]) if trial["EnrollmentCount"] else 0,
                    start_date=trial["StartDate"][0] if trial["StartDate"] else "Unknown",
                    country=trial["LocationCountry"][0] if trial["LocationCountry"] else "Unknown"
                )
                session.add(new_trial)

        session.commit()
        session.close()
        return f"Fetched {len(trials)} new trials."

    return "Failed to fetch trials."