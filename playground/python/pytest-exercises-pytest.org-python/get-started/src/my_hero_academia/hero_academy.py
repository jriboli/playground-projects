
class Student:
    def __init__(self, name, quirk):
        self.name = name
        self.quirk = quirk
        self.progress = 0

    def train(self, hours):
        self.progress += hours


class HeroAcademy:
    def __init__(self):
        self.students = []

    def enroll_student(self, student):
        self.students.append(student)

    def get_student_by_name(self, name):
        return next((s for s in self.students if s.name == name), None)

    def conduct_training(self, hours):
        for student in self.students:
            student.train(hours)

