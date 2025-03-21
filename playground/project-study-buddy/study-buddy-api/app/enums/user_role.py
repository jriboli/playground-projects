from enum import Enum

class UserRole(str, Enum):
    admin = "admin"
    instructor = "instructor"
    student = "student"