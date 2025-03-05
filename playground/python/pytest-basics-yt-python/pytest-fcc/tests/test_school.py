import pytest
from src.school import Classroom, Student, Teacher, TooManyStudents


pytestmark = [pytest.mark.harrypotter]


# Fixtures
@pytest.fixture
def hogwarts_class():
    teacher = Teacher("Minerva McGonagall")
    students = [
        Student("Harry Potter"),
        Student("Hermione Granger"),
        Student("Ron Weasley"),
        Student("Draco Malfoy")
    ]
    return Classroom(teacher, students, "Defense Against the Dark Arts")


@pytest.fixture
def new_student():
    return Student("Luna Lovegood")


@pytest.fixture
def new_teacher():
    return Teacher("Severus Snape")


# Tests
@pytest.mark.parametrize("student_name", ["Harry Potter", "Hermione Granger", "Draco Malfoy"])
def test_remove_student(hogwarts_class, student_name):
    hogwarts_class.remove_student(student_name)
    assert all(student.name != student_name for student in hogwarts_class.students)


@pytest.mark.parametrize("student_name", ["Neville Longbottom", "Cho Chang"])
def test_add_student(hogwarts_class, student_name):
    new_student = Student(student_name)
    hogwarts_class.add_student(new_student)
    assert any(student.name == student_name for student in hogwarts_class.students)


def test_add_student_raises_exception_on_limit(hogwarts_class):
    additional_students = [Student(f"Student{i}") for i in range(8)]
    hogwarts_class.students.extend(additional_students)

    with pytest.raises(TooManyStudents):
        hogwarts_class.add_student(Student("Fred Weasley"))


def test_change_teacher(hogwarts_class, new_teacher):
    hogwarts_class.change_teacher(new_teacher)
    assert hogwarts_class.teacher.name == new_teacher.name


# Edge Case: Adding a student after hitting the limit of 10
def test_cannot_add_student_beyond_limit(hogwarts_class):
    additional_students = [Student(f"Student{i}") for i in range(7)]
    hogwarts_class.students.extend(additional_students)
    assert len(hogwarts_class.students) == 11

    with pytest.raises(TooManyStudents):
        hogwarts_class.add_student(Student("George Weasley"))
