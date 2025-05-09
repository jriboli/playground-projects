import pytest
from pages.web.swagbucks.login_page import LoginPage


@pytest.mark.web
@pytest.mark.smoke
@pytest.mark.flaky(rerun=2)
def test_login_valid_user(playwright_browser):
    page = playwright_browser.new_page()
    login_page = LoginPage(page)
    login_page.navigate().login_as("user", "pass")
    assert page.url.endswith("/dashboard")


@pytest.mark.web
@pytest.mark.parametrize("username,password", [
    ("admin", "admin123"),
    ("user", "userpass"),
    ("wronguser", "wrongpass")
])
def test_login_various_users(playwright_browser, username, password):
    page = playwright_browser.new_page()
    login = LoginPage(page)
    login.login_as(username, password)
    assert "dashboard" in page.url or "error" in page.content()