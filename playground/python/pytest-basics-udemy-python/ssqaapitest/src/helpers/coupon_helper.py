

import logging as logger

from src.utilities.wooAPIUtility import WooAPIUtility
from src.utilities.genericUtilities import generate_random_string
from src.dao.coupon_dao import CouponDAO

class CouponHelper(object):

    def __init__(self):
        self.woo_helper = WooAPIUtility()

    def get_or_create_coupon(self, coupon_code=None, coupon_type=None, coupon_amount=None):
        if not coupon_code:
            coupon_code = generate_random_string()
        if not coupon_type:
            coupon_type = "percent"
        if not coupon_amount:
            coupon_amount = "1"

        # Check if coupon already exists
        coupon_db_check = CouponDAO().get_coupon_with_filter(coupon_code)
        if len(coupon_db_check) == 0:
            payload = {"code": coupon_code,
                       "discount_type": coupon_type,
                       "amount": coupon_amount,
                       "minimum_amount": "1.00"}

            rs_api = self.woo_helper.post(wc_endpoint="coupons", params=payload, expected_status_code=201)
            return rs_api
        else:
            return self.get_coupon_by_id(coupon_db_check[0]['ID'])

    def get_coupon_by_id(self, coupon_id):
        rs_api = self.woo_helper.get(wc_endpoint=f"coupons/{coupon_id}", expected_status_code=200)
        return rs_api
