
import pytest
import logging as logger
from src.helpers.product_helper import ProductHelper


pytestmark = []


@pytest.fixture(scope="module")
def setup():
    product_helper = ProductHelper()
    info = {'product_helper': product_helper}
    return info


def test_get_all_products(setup):
    rs_api = setup['product_helper'].get_all_products()

    