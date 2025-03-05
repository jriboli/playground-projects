
from celery import Celery

celery = Celery(
    "clinical_trials",
    broker="redis://redis:6379/0",
    backend="redis://redis:6379/0",
    include=["backend.tasks.fetch_trials", "backend.tasks.send_notifications"],
)

celery.conf.update(
    task_serializer="json",
    accept_content=["json"],
    result_serializer="json",
)