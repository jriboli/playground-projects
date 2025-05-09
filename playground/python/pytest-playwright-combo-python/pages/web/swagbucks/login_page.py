from playwright.sync_api import Page

class LoginPage:
    def __init__(self, page: Page):
        self.page = page

    def navigate(self):
        self.page.goto("https://swagbucks.com")
        return self

    def login_as(self, username, password):
        if self.page.get_by_text("Already have an account").is_visible():
            self.page.get_by_text("Log In").last.click()

        self.page.fill('#sbxJxRegEmail', username)
        self.page.fill('#sbxJxRegPswd', password)
        self.page.click('#loginBtn')

        #self.page.pause()
        return self

