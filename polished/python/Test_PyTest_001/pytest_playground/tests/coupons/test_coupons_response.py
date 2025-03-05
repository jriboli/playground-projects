import pytest
from src.utilities.wooAPIUtility import WooAPIUtility


pytestmark = [pytest.mark.coupons, pytest.mark.structure]


@pytest.fixture(scope="module")
def response():
    return WooAPIUtility().get("coupons")


@pytest.mark.tcid10
def test_response_contains_coupon_code(response):
    json_data = response[0]
    assert "code" in json_data, f"Response JSON should contain 'code'"


@pytest.mark.tcid11
def test_response_contains_discount(response):
    json_data = response[0]
    assert "amount" in json_data, f"Response JSON should contain 'amount'"


@pytest.mark.tcid12
def test_response_contains_id(response):
    json_data = response[0]
    assert "id" in json_data, f"Response JSON should contain 'id'"
    assert isinstance(json_data['id'], int), f"Coupon 'id' is expected to be of value integer"


@pytest.mark.tcid13
def test_response_contains_discount_type(response):
    discount_types = {'fixed_product', 'percent', 'fixed_cart'}
    json_data = response[0]
    assert "discount_type" in json_data, f"Response JSON should contain 'discount_type'"
    assert json_data['discount_type'] in discount_types, f"Response 'discount_type' not within allowed " \
        f"enums - {json_data['discount_type']}"

