
import pytest


# Hogwarts students and magical items
class HogwartsStudent:
    def __init__(self, name, house):
        self.name = name
        self.house = house


class MagicalItem:
    def __init__(self, item_name):
        self.item_name = item_name


# Fixtures
@pytest.fixture(scope="session", autouse=True)
def magical_environment():
    print("\nSetting up the magical environment for Hogwarts!")
    # Global magical setup, like enchantments on Hogwarts Castle
    yield
    print("\nTearing down the magical environment after all tests.")


@pytest.fixture(scope="module", autouse=True)
def classroom_setup():
    print("\nPreparing the Defense Against the Dark Arts classroom!")
    yield
    print("\nCleaning up the classroom.")


@pytest.fixture(scope="function", autouse=True)
def prepare_student():
    # Every test implicitly starts with a Gryffindor student named Harry
    print("\nStarting a test with a Gryffindor student named Harry Potter")
    student = HogwartsStudent(name="Harry Potter", house="Gryffindor")
    yield student
    print("\nCleaning up student after test.")

