import requests
import os

JIRA_BASE_URL = os.getenv('JIRA_URL')
JIRA_TOKEN = os.getenv('JIRA_TOKEN')
JIRA_HEADERS = {
    'Authorization': f"Bearer {JIRA_TOKEN}",
    'Content-Type': 'application/json'
}


def report_to_jira(test_case_id, status, comment):
    result = {
        "testCaseKey": test_case_id,
        "status": status,
        "comment": comment
    }
    response = requests.post(f"{JIRA_BASE_URL}/rest/atm/1.0/testresult", json=result, headers=JIRA_HEADERS)
    return response.status_code