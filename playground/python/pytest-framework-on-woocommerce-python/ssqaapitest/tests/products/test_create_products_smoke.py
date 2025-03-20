
import pytest
import logging as logger

from src.utilities.genericUtilities import generate_random_string
from src.helpers.product_helper import ProductHelper
from src.dao.products_dao import ProductsDAO


pytestmark = [pytest.mark.products, pytest.mark.smoke]


@pytest.mark.tcid26
def test_create_products_simple():

    # generate some data
    payload = dict()
    payload['name'] = generate_random_string(10)
    payload['type'] = 'simple'
    payload['regular_price'] = "9.99"

    # make a call
    product_rs = ProductHelper().create_product(payload=payload)
    logger.debug(f"Response of create product: {product_rs}")

    # verify the response is not empty
    assert product_rs, f"Response of creating product is empty. Payload {payload}"
    assert product_rs['name'] == payload['name'], f"Create product api call reasponse has" \
        f"unexpected name. Expected: {payload['name']}, Actual: {product_rs['name']}"

    # verify the product in db
    product_dao = ProductsDAO()
    product_db = product_dao.get_product_by_id(product_id=product_rs['id'])

    assert len(product_db) == 1, "There exists more or less than 1 record in the DB"
    assert product_db[0]['post_title'] == payload['name'], f"Create product record in DB has" \
        f"unexpected name. Expected: {payload['name']}, Actual: {product_db[0]['post_title']}"





