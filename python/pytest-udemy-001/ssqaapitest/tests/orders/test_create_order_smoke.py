import pytest
import math
import logging as logger
from src.dao.products_dao import ProductsDAO
from src.helpers.orders_helper import OrdersHelper
from src.helpers.coupon_helper import CouponHelper
from src.dao.orders_dao import OrdersDao
from src.helpers.customers_helper import CustomerHelper

pytestmark = [pytest.mark.orders, pytest.mark.smoke]

@pytest.fixture(scope='module')
def my_orders_smoke_setup():
    # get product
    rand_product = ProductsDAO().get_random_product_from_db()
    product_id = rand_product[0]['ID']

    info = {'product_id': product_id}
    return info

@pytest.mark.tcid48
def test_create_paid_order_guest_user(my_orders_smoke_setup):
    product_id = my_orders_smoke_setup['product_id']
    # make the call
    info = {"line_items": [
        {
            "product_id": product_id,
            "quantity": 1
        }
    ]}
    order_json = OrdersHelper().create_order(additional_args=info)

    # verify
    order_db = OrdersDao().get_order_by_id(order_json['id'])
    OrdersHelper().verify_order_is_created(order_json=order_json, order_db=order_db,
                                           exp_cust_id=0, exp_product=order_json['line_items'])


@pytest.mark.tcid49
def test_create_paid_order_new_created_user(my_orders_smoke_setup):
    # create user
    rand_user = CustomerHelper().create_customer()

    product_id = my_orders_smoke_setup['product_id']

    # make the call
    info = {"line_items": [
        {
            "product_id": product_id,
            "quantity": 1
        }],
        "customer_id": rand_user['id']
    }
    order_json = OrdersHelper().create_order(additional_args=info)

    # verify
    order_db = OrdersDao().get_order_by_id(order_json['id'])
    OrdersHelper().verify_order_is_created(order_json=order_json, order_db=order_db,
                                           exp_cust_id=rand_user['id'], exp_product=order_json['line_items'])

@pytest.mark.tcid60
def test_create_paid_order_with_coupon_50_percent(my_orders_smoke_setup):
    product_id = my_orders_smoke_setup['product_id']
    coupon = CouponHelper().get_or_create_coupon()

    # make the call
    info = {"line_items": [
        {
            "product_id": product_id,
            "quantity": 1
        }],
        "coupon_lines": [
        {
            "code": coupon['code']
        }]
    }
    order_json = OrdersHelper().create_order(additional_args=info)

    # verify
    product_cost = order_json['line_items'][0]['subtotal']
    shipping_cost = order_json['shipping_total']
    order_total = order_json['total']

    # apply some rounding
    discount_cost = math.ceil(float(product_cost) * (50/100) * 100) / 100

    logger.debug(f"Discount Cost: {discount_cost}, Shipping Cost: {shipping_cost}, Total: {order_total}")
    assert discount_cost + float(shipping_cost) == float(order_total) , f"The discount was not applied. " \
        f"Discount Cost: {discount_cost}, Shipping Cost: {shipping_cost}, Total: {order_total}"
