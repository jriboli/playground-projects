
import logging as logger
from src.utilities.requestsUtility import RequestsUtility
from src.utilities.wooAPIUtility import WooAPIUtility


class ProductHelper(object):

    def __init__(self):
        self.request_utility = RequestsUtility()
        self.woo_helper = WooAPIUtility()

    def get_product_by_id(self, product_id):
        return self.request_utility.get(f"products/{product_id}")

    def create_product(self, payload):
        return self.request_utility.post('products', payload=payload, expected_status_code=201)

    def get_products_with_filter(self, payload):

        max_pages = 1000
        all_products = []
        for i in range(1, max_pages + 1):
            logger.debug(f"List products page number: {i}")

            if not 'per_page' in payload.keys():
                payload['per_page'] = 3

            payload['page'] = i
            rs_api = self.request_utility.get('products', payload=payload)

            if not rs_api:
                break
            else:
                all_products.extend(rs_api)
        else:
            raise Exception(f"Unable to find all products after {max_pages} pages.")

        return all_products

    def update_product(self, product_id, additional_args=None):
        payload = dict()
        # if user adds more info to payload, update
        if additional_args:
            assert isinstance(additional_args, dict), f"Parameter 'additional_args' must be a dictionary, but found {type(additional_args)}"
            payload.update(additional_args)

        rs_api = self.woo_helper.put(f"products/{product_id}", params=payload)
        return rs_api
