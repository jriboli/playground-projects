import pytest
import src.shapes as shapes

pytestmark = [pytest.mark.rectangle]


## MOVED TO CONFTEST FILE - FOR GLOBAL ACCESS
# @pytest.fixture()
# def my_rectangle():
#     return shapes.Rectangle(10, 20)
#
#
# @pytest.fixture()
# def weird_rectangle():
#     return shapes.Rectangle(5,6)


def test_area(my_rectangle):
    #rectangle = shapes.Rectangle(10, 20)
    assert my_rectangle.area() == 10 * 20


def test_perimeter(my_rectangle):
    #rectangle = shapes.Rectangle(20, 10)
    assert my_rectangle.perimeter() == (20 * 2) + (10 * 2)


def test_not_equal(my_rectangle, weird_rectangle):
    assert my_rectangle != weird_rectangle
