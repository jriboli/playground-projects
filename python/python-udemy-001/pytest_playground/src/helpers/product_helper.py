
from src.utilities.wooAPIUtility import WooAPIUtility


class ProductHelper(object):

    def __init__(self):
        self.woo_helper = WooAPIUtility()

    def get_all_products(self):
        rs_api = self.woo_helper.get(wc_endpoint="products")
        return rs_api