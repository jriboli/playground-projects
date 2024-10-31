
import pytest
from src.my_hero_academia.hero_database import HeroAcademy, HeroDatabase


pytestmark = [pytest.mark.monkey]

mock_heroes_data = [
    {"name": "Deku", "quirk": "One For All"},
    {"name": "Bakugo", "quirk": "Explosion"},
    {"name": "Todoroki", "quirk": "Half-Cold Half-Hot"},
]


def mock_fetch_heroes_by_quirk(self, quirk):
    # Mock function to simulate fetching heroes by quirk
    return [hero for hero in mock_heroes_data if hero["quirk"] == quirk]


def test_get_heroes_with_quirk(monkeypatch):
    # Setup: Use monkeypatch to replace the fetch_heroes_by_quirk method in HeroDatabase
    monkeypatch.setattr(HeroDatabase, "fetch_heroes_by_quirk", mock_fetch_heroes_by_quirk)

    # Instantiate HeroAcademy with the patched HeroDatabase
    hero_db = HeroDatabase()
    hero_academy = HeroAcademy(hero_db)

    heroes = hero_academy.get_heroes_with_quirk("Explosion")
    assert len(heroes) == 1
    assert heroes[0]["name"] == "Bakugo"

