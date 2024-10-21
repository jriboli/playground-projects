
import pytest
import logging as logger
from src.dao.products_dao import ProductsDAO
from src.helpers.orders_helper import OrdersHelper
from src.dao.orders_dao import OrdersDao

pytestmark = [pytest.mark.orders, pytest.mark.smoke]

@pytest.mark.tcid48
def test_create_paid_order_guest_user():

    # get product
    rand_product = ProductsDAO().get_random_product_from_db()
    product_id = rand_product[0]['ID']

    # make the call
    info = {"line_items": [
        {
            "product_id": product_id,
            "quantity": 1
        }
    ]}
    order_rs = OrdersHelper().create_order(additional_args=info)

    # verify response
    assert order_rs, f"Create order response is empty."
    assert order_rs['customer_id'] == 0, f"Created order as guest, response had unexpected value for 'customer_id'"
    assert len(order_rs['line_items']) == 1, f"Number of items in order should be one, received unexpected value - {len(order_rs['line_items'])}"
    assert order_rs['line_items'][0]['product_id'] == product_id, f"The response product id did not match expected. Expected {product_id}," \
            f"Actual {order_rs['line_items'][0]['product_id'] }"

    # verify db
    order_db = OrdersDao().get_order_by_id(order_rs['id'])

    assert order_db, f"Create order, line items not found in DB, Order id: {order_rs['id']}"

    line_info = [i for i in order_db if i['order_item_type'] == 'line_item']
    assert line_info[0]['order_item_name'] == rand_product[0]['post_title'], f"The item name doesn ot match expected." \
        f"Expected: {rand_product[0]['post_title']}, Actual: {line_info[0]['order_item_name']}"

