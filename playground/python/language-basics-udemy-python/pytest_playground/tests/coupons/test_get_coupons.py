
import pytest
from pytest import fixture
import logging as logger
from src.helpers.coupon_helper import CouponHelper

pytestmark = [pytest.mark.coupons]


@fixture()
def setup():
    coupon_helper = CouponHelper()
    info = {'coupon_helper': coupon_helper}
    return info


@pytest.mark.tcid1
def test_get_all_coupons(setup):
    rs_api = setup['coupon_helper'].get_all_coupons()


@pytest.mark.tcid2
def test_get_all_coupons_with_per_page(setup):
    per_page_limit = 2
    filter = {'per_page': per_page_limit}
    rs_api = setup['coupon_helper'].get_coupons_with_filter(filter)

    assert len(rs_api) == per_page_limit, f"Failed to return expected per_page. " \
        f"Expected: {per_page_limit}, Actual: {len(rs_api)}."

