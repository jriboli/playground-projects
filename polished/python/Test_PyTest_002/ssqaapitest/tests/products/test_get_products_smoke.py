
import pytest
import logging as logger
from src.utilities.requestsUtility import RequestsUtility
from src.dao.products_dao import ProductsDAO

pytestmark = [pytest.mark.products, pytest.mark.smoke]


@pytest.mark.tcid24
def test_get_all_products():
    req_helper = RequestsUtility()
    rs_api = req_helper.get('products')
    logger.debug(f"Response of list all: {rs_api}")

    assert rs_api, f"Response of list all products is empty."


@pytest.mark.tcid25
def test_get_product_by_id():
    # get existing product from db
    product_dao = ProductsDAO()
    existing_product = product_dao.get_random_product_from_db()
    existing_id = existing_product[0]['product_id']

    req_helper = RequestsUtility()
    rs_api = req_helper.get(endpoint=f"products/{existing_id}")
    logger.debug(f"Response of list all: {rs_api}")

    assert rs_api['id'] == existing_id, f"Product id returned from DB did not match. " \
    f"Expected: {existing_id} verses Actual: {rs_api['id']}"
    import pdb; pdb.set_trace()



