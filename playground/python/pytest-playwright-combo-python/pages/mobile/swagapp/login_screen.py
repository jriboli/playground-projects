class LoginScreen:
    def __init__(self, driver):
        self.driver = driver

    def login_as(self,username, password):
        self.driver.find_element_by_accessibility_id('username').send_keys(username)
        self.driver.find_element_by_accessibility_id('password').send_keys(password)
        self.driver.find_element_by_accessibility_id('login').click()
        return self

