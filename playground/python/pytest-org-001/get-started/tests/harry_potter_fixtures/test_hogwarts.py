
import pytest
from tests.harry_potter_fixtures.conftest import HogwartsStudent, MagicalItem


pytestmark = [pytest.mark.harry]


def test_student_house(prepare_student):
    # 'prepare_student' is passed implicitly and provides a default student
    assert prepare_student.house == "Gryffindor", "Expected Harry to be in Gryffindor house."


def test_student_name(prepare_student):
    # Verify that the name of the student is 'Harry Potter'
    assert prepare_student.name == "Harry Potter", "Expected the student's name to be Harry Potter"


def test_magical_item_presence():
    # No need to request any fixtures; autouse takes care of the setup
    item = MagicalItem("Wand")
    print(f"\nTest has access to a magical item: {item.item_name}")
    assert item.item_name == "Wand"


@pytest.fixture
def spellbook():
    # A non-autouse fixture that must be explicitly requested
    return ["Expelliarmus", "Lumos", "Accio"]


def test_spell_in_spellbook(spellbook):
    # Explicitly requesting the spellbook fixture to verify it contains specific spells
    assert "Lumos" in spellbook, "Expected 'Lumos' in the spellbook!"
    assert "Expelliarmus" in spellbook, "Expected 'Expelliarmus' in the spellbook!"

