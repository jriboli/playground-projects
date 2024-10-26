
from src.utilities.wooAPIUtility import WooAPIUtility


class CouponHelper(object):

    def __init__(self):
        self.woo_helper = WooAPIUtility()

    def get_all_coupons(self):
        rs_api = self.woo_helper.get(wc_endpoint="coupons")
        return rs_api

    def get_coupons_with_filter(self, additional_args):
        rs_api = self.woo_helper.get(wc_endpoint="coupons", params=additional_args)
        return rs_api

