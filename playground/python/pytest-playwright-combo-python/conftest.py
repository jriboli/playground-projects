import pytest
from playwright.sync_api import sync_playwright
from appium import webdriver as appium_webdriver
import requests
from dotenv import load_dotenv
import os

load_dotenv()


# Web fixture
@pytest.fixture(scope="function")
def playwright_browser():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False, slow_mo=2000)
        yield browser
        browser.close()


# Mobile fixture (Android example)
@pytest.fixture(scope="function")
def android_driver():
    desired_caps = {
        "platformName": "Android",
        "deviceName": "Android Emulator",
        "app": os.getenv("ANDROID_APP_PATH"),
        "automationName": "UiAutomator2"
    }
    driver = appium_webdriver.Remote("http://localhost:4723/wd/hub", desired_caps)
    yield driver
    driver.quit()


# API fixture
@pytest.fixture(scope="function")
def api_client():
    base_url = os.getenv("API_BASE_URL")
    session = requests.Session()
    session.headers.update({"Authorization": f"Bearer {os.getenv('API_TOKEN')}"})
    return lambda endpoint, **kwargs: session.request(url=f"{base_url}/{endpoint}", **kwargs)
