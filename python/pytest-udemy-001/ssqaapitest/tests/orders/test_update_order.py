
import pytest

from src.helpers.orders_helper import OrdersHelper
from src.dao.orders_dao import OrdersDao

pytestmark = [pytest.mark.orders, pytest.mark.smoke]

@pytest.mark.tcid55
def test_update_order_status():
    new_status = "completed"
    order_helper = OrdersHelper()

    # get a order
    order_json = order_helper.create_order()
    order_id = order_json['id']

    # make call
    update_json = order_helper.update_order(order_id, new_status)

    # verify response
    assert update_json['status'] == new_status, f"API response status did not match expected - {new_status}"

    info_json = order_helper.get_order_info(order_id)
    assert info_json['status'] == new_status, f"API response info did not match expected - {new_status}"

    import pdb; pdb.set_trace()
