
# Each test has a unique instance of the class
# Promotes test isolation

class TestPyTestClass:
    value = 0

    def test_one(self):
        self.value = 1
        assert self.value == 1

    def test_two(self):
        assert self.value == 1


# Notice, test_one will pass
# But test_two will fail

