
import pytest
import logging as logger
from src.helpers.coupon_helper import CouponHelper
from src.utilities.wooAPIUtility import WooAPIUtility
from src.utilities.genericUtilities import generate_random_string

pytestmark = [pytest.mark.coupons, pytest.mark.smoke]


@pytest.mark.parametrize('discount_type', [pytest.param('percent', marks=pytest.mark.tcid37),
                                           pytest.param('fixed_cart', marks=pytest.mark.tcid38),
                                           pytest.param('fixed_product', marks=pytest.mark.tcid39)])
def test_create_coupon_with_discount_type(discount_type):
    coupon_helper = CouponHelper()

    coupon_json = coupon_helper.get_or_create_coupon(coupon_type=discount_type)
    coupon_id = coupon_json['id']
    assert coupon_json['discount_type'] == discount_type, f"Create coupon response has incorrect value for "\
        f"discount_type. Expected: {discount_type}, Actual: {coupon_json['discount_type']}"

    info_json = coupon_helper.get_coupon_by_id(coupon_id)
    assert info_json['discount_type'] == discount_type, f"Get coupon info has incorrect value for " \
        f"discount_type. Expected: {discount_type}, Actual: {info_json['discount_type']}"

    logger.debug(f"Expected: {discount_type}, Actual: {info_json['discount_type']}")

@pytest.mark.tcid40
def test_create_coupon_with_invalid_discount_type():
    payload = {"code": generate_random_string(),
               "discount_type": generate_random_string(),
               "amount": "1.00",
               "minimum_amount": "1.00"}

    rs_api = WooAPIUtility().post(wc_endpoint="coupons", params=payload, expected_status_code=400)

    assert rs_api['code'] == 'rest_invalid_param', f"Incorrect code response. Expected: " \
        f"'rest_invalid_param'"
    assert rs_api['message'] == 'Invalid parameter(s): discount_type', f"Incorrect message returned. " \
        f"Expected: 'Invalid parameter(s): discount_type'"

    #logger.debug(rs_api)
