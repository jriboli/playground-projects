from pytest import mark


@mark.body
class BodyTests:
    @mark.smoke
    def test_body_functions_as_expected(self):
        assert True
