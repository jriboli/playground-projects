
import smtplib
import os
from twilio.rest import Client

TWILIO_SID = os.getenv("TWILIO_SID")
TWILIO_AUTH = os.getenv("TWILIO_AUTH")
TWILIO_PHONE = os.getenv("TWILIO_PHONE")

def send_email(to_email, subject, body):
    """
    Sends an email notification.
    """
    smtp_server = smtplib.SMTP(os.getenv("SMTP_SERVER"), 587)
    smtp_server.starttls()
    smtp_server.login(os.getenv("SMTP_USER"), os.getenv("SMTP_PASS"))
    smtp_server.sendmail(os.getenv("SMTP_USER"), to_email, f"Subject: {subject}\n\n{body}")
    smtp_server.quit()

def send_sms(to_phone, body):
    """
    Sends an SMS notification using Twilio.
    """
    client = Client(TWILIO_SID, TWILIO_AUTH)
    client.messages.create(
        body=body,
        from_=TWILIO_PHONE,
        to=to_phone
    )