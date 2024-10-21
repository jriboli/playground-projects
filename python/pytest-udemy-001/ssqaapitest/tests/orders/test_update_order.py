import pytest

from src.helpers.orders_helper import OrdersHelper
from src.utilities.wooAPIUtility import WooAPIUtility

pytestmark = [pytest.mark.orders, pytest.mark.smoke, pytest.mark.params]


# @pytest.mark.tcid55
@pytest.mark.parametrize("new_status", [pytest.param('cancelled', marks=pytest.mark.tcid55),
                                        pytest.param('completed', marks=pytest.mark.tcid56),
                                        pytest.param('on-hold', marks=pytest.mark.tcid57)])
def test_update_order_status(new_status):
    # new_status = "completed"
    order_helper = OrdersHelper()

    # get a order
    order_json = order_helper.create_order()
    order_id = order_json['id']

    # make call
    update_json = order_helper.update_order(order_id, {"status": new_status})

    # verify response
    assert update_json['status'] == new_status, f"API response status did not match expected - {new_status}"

    info_json = order_helper.get_order_info(order_id)
    assert info_json['status'] == new_status, f"API response info did not match expected - {new_status}"


@pytest.mark.negative
@pytest.mark.tcid58
def test_update_order_status_to_random_string():
    new_status = "abcdefg"
    order_helper = OrdersHelper()

    # get a order
    order_json = order_helper.create_order()
    order_id = order_json['id']

    # make call
    payload = {"status": new_status}
    update_json = WooAPIUtility().put(f"orders/{order_id}", params=payload, expected_status_code=400)

    # verify response
    assert update_json['code'] == 'rest_invalid_param', "Update order with invalid status, gave incorrect code in " \
        f"response - {update_json['code']}"
    assert update_json['message'] == 'Invalid parameter(s): status', "Update order with invalid status, gave " \
        f"incorrect message in response - {update_json['message']}"


@pytest.mark.tcid59
def test_update_order_customer_note():
    customer_note = "This is a customer note"
    order_helper = OrdersHelper()

    # get a order
    order_json = order_helper.create_order()
    order_id = order_json['id']

    # make call
    update_json = order_helper.update_order(order_id, {"customer_note": customer_note})

    # verify response
    assert update_json['customer_note'] == customer_note, f"Update orders response customer_note did not match" \
        f" expected - {customer_note}"

    info_json = order_helper.get_order_info(order_id)
    assert info_json['customer_note'] == customer_note, f"Get orders info response customer_note did not match" \
        f" expected - {customer_note}"

