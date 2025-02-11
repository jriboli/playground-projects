FROM ubuntu:latest
LABEL authors="Justin"

ENTRYPOINT ["top", "-b"]

# Use an official Python image
FROM python:3.10

# Set working directory
WORKDIR /app

# Copy and install dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
RUN pip install twilio

# Copy the rest of the application
COPY . .

# Run the FastAPI app
CMD ["uvicorn", "backend.api.main:app", "--host", "0.0.0.0", "--port", "8000"]