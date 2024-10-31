
import pytest
from src.my_hero_academia.hero_academy import HeroAcademy, Student


@pytest.fixture
def hero_academy():
    return HeroAcademy()


@pytest.fixture
def student_factory():
    def create_student(name, quirk):
        return Student(name, quirk)
    return create_student


def test_enroll_student_with_quirk(hero_academy, student_factory):
    deku = student_factory("Deku", "One for All")
    hero_academy.enroll_student(deku)

    enrolled_student = hero_academy.get_student_by_name("Deku")
    assert enrolled_student is not None
    assert enrolled_student.quirk == "One for All"

