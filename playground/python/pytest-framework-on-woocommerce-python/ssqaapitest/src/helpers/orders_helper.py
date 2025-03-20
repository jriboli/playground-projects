
import os
import json
from src.utilities.wooAPIUtility import WooAPIUtility

class OrdersHelper(object):

    def __init__(self):
        self.cur_file_dir = os.path.dirname(os.path.realpath(__file__))
        self.woo_helper = WooAPIUtility()

    def create_order(self, additional_args=None):
        payload_template = os.path.join(self.cur_file_dir, "..", "data", "create_order_payload.json")

        with open(payload_template) as f:
            payload = json.load(f)

        # if user adds more info to payload, update
        if additional_args:
            assert isinstance(additional_args, dict), f"Parameter 'additional_args' must be a dictionary, but found {type(additional_args)}"
            payload.update(additional_args)

        rs_api = self.woo_helper.post('orders', params=payload, expected_status_code=201)

        return rs_api

    def update_order(self, order_id, additional_args=None):
        payload = dict()
        # if user adds more info to payload, update
        if additional_args:
            assert isinstance(additional_args, dict), f"Parameter 'additional_args' must be a dictionary, but found {type(additional_args)}"
            payload.update(additional_args)

        rs_api = self.woo_helper.put(f"orders/{order_id}", params=payload)
        return rs_api

    def get_order_info(self, order_id):
        return self.woo_helper.get(f"orders/{order_id}")

    def verify_order_is_created(self, order_json, order_db, exp_cust_id, exp_product):
        # API Response
        assert order_json, f"Create order response is empty."
        assert order_json['customer_id'] == exp_cust_id, f"Created order as new user, response had unexpected value" \
            f"for 'customer_id' - Expected {exp_cust_id}"
        assert len(order_json['line_items']) == len(exp_product), f"Number of items in order should be" \
            f"{len(exp_product)}, received unexpected value - {len(order_json['line_items'])}"
        assert order_json['line_items'][0]['product_id'] == exp_product[0]['product_id'], f"The response product id did not" \
            f"match expected. Expected {exp_product[0]['product_id']}, Actual {order_json['line_items'][0]['product_id']}"

        # Database
        assert order_db, f"Create order, line items not found in DB, Order id: {order_json['id']}"
        line_info = [i for i in order_db if i['order_item_type'] == 'line_item']

        # get list of Product Ids in the response
        api_product_ids = [i['product_id'] for i in order_json['line_items']]
        for product in exp_product:
            assert product['product_id'] in api_product_ids, f"Created order does not have at least 1 expected" \
                f"product in DB. Product ID: {product['product_id']}, Order Id: {order_json['id']}"

        # assert line_info[0]['order_item_name'] == exp_product[0]['post_title'], f"The item name doesnt match" \
        #     f"expected. Expected: {exp_product[0]['post_title']}, Actual: {line_info[0]['order_item_name']}"


