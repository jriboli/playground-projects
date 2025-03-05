
import requests


class HeroDatabase:
    def __init__(self):
        self.database_url = "http://hero-api.com/heroes"

    def fetch_heroes_by_quirk(self, quirk):
        # This function would fetch heroes with a specific quick from a remote database
        response = requests.get(f"{self.database_url}?quirk={quirk}")
        if response.status_code == 200:
            return response.json()
        return []


class HeroAcademy:
    def __init__(self, hero_db):
        self.hero_db = hero_db

    def get_heroes_with_quirk(self, quirk):
        return self.hero_db.fetch_heroes_by_quirk(quirk)
