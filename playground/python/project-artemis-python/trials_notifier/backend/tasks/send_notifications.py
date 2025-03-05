
#from celery import Celery
from backend.tasks.celery_app import celery
from backend.api.models.alert import Alert
from backend.api.models.trial import Trial
from backend.api.models.user import User
from backend.api.utils.db import get_db_session
from backend.api.services.notification import send_email, send_sms
import os

#app = Celery("tasks", broker=os.getenv("CELERY_BROKER_URL", "redis://localhost:6379/0"))


@celery.task
def send_notifications():
    """
    Sends notifications to users based on their custom filters.
    """
    session = get_db_session()

    # Get all user alerts
    alerts = session.query(Alert).all()
    notifications_sent = 0

    for alert in alerts:
        # Find trials matching user-defined filters
        matching_trials = session.query(Trial).filter(
            (Trial.condition == alert.condition) |
            (Trial.phase == alert.phase) |
            (Trial.enrollment >= alert.min_enrollment)
        ).all()

        if matching_trials:
            user = session.query(User).filter_by(auth0_id=alert.auth0_id).first()
            if user:
                message = f"New trials matching your alert:\n"
                message += "\n".join([f"{t.nct_id}: {t.title}" for t in matching_trials])

                # Send notification via email or SMS
                if alert.notify_via == "email":
                    send_email(user.email, "New Clinical Trials Found", message)
                elif alert.notify_via == "sms":
                    send_sms(user.phone, message)

                notifications_sent += 1

    session.close()
    return f"Sent {notifications_sent} notifications."