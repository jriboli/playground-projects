
import pytest
from src.my_hero_academia.hero_academy import HeroAcademy, Student


pytestmark = [pytest.mark.hero]


@pytest.fixture(scope="module")
def hero_academy():
    academy = HeroAcademy()

    academy.enroll_student(Student("Deku", "One for All"))
    academy.enroll_student(Student("Bakugo", "Explosion"))
    academy.enroll_student(Student("Todoroki", "Half-Cold Half-Hot"))
    return academy


def test_initial_students_enrolled(hero_academy):
    deku = hero_academy.get_student_by_name("Deku")
    bakugo = hero_academy.get_student_by_name("Bakugo")
    assert deku is not None
    assert bakugo is not None
    assert deku.quirk == "One for All"
    assert bakugo.quirk == "Explosion"


def test_student_progress_increases(hero_academy):
    hero_academy.conduct_training(2)
    todoroki = hero_academy.get_student_by_name("Todoroki")
    assert todoroki.progress == 2


def test_enroll_new_student(hero_academy):
    new_student = Student("Uraraka", "Zero Gravity")
    hero_academy.enroll_student(new_student)
    uraraka = hero_academy.get_student_by_name("Uraraka")
    assert uraraka is not None
    assert uraraka.quirk == "Zero Gravity"

