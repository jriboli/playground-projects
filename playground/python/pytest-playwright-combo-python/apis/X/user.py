import requests

class UserAPI:
    def __init__(self):
        self.base_url = ""
        self.headers = {}
        self.user_id = None

    def with_token(self, token):
        self.headers['Authorization'] = f"Bearer {token}"
        return self

    def with_user_id(self, user_id):
        self.user_id = user_id
        return self

    def get_user(self):
        return requests.get(f"{self.base_url}/users/{self.user_id}", headers=self.headers)