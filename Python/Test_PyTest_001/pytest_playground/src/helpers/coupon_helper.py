
from src.utilities.wooAPIUtility import WooAPIUtility
from src.utilities.databaseUtility import DBUtility


class CouponHelper(object):

    def __init__(self):
        self.woo_helper = WooAPIUtility()
        self.db_helper = DBUtility()

    def get_all_coupons(self):
        rs_api = self.woo_helper.get(wc_endpoint="coupons")
        return rs_api

    def get_coupon_by_id(self, coupon_id):
        rs_api = self.woo_helper.get(wc_endpoint=f"coupons/{coupon_id}")
        return rs_api

    def get_coupons_with_filter(self, additional_args):
        rs_api = self.woo_helper.get(wc_endpoint="coupons", params=additional_args)
        return rs_api

    def find_or_create_coupon(self, coupon_code):
        coupon = self.get_coupon_by_id()

