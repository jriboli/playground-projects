
import pytest
import src.shapes as shapes


@pytest.mark.square
@pytest.mark.parametrize("side_length, expected_area", [
    (5, 25),
    (6, 36)
])
def test_multiple_square_areas(side_length, expected_area):
    assert shapes.Square(side_length).area() == expected_area
