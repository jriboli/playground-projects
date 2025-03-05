
import pytest
from src.helpers.product_helper import ProductHelper
from src.utilities.genericUtilities import generate_random_string


pytestmark = [pytest.mark.products, pytest.mark.smoke]

@pytest.mark.tcid61
def test_update_product_regular_price():
    product_helper = ProductHelper()

    # create simple product
    # generate some data
    payload = dict()
    payload['name'] = generate_random_string(10)
    payload['type'] = 'simple'
    payload['regular_price'] = "9.99"
    product_json = product_helper.create_product(payload=payload)

    updated_price = "8.95"

    # make call to update
    update_json = product_helper.update_product(product_json['id'], {'regular_price': updated_price})
    assert update_json['regular_price'] == updated_price, f"In the Update Product responce the price was not " \
        f"updated as expected. Expected: {updated_price}, Actual: {update_json['regular_price']}"

    # verify response
    info_json = product_helper.get_product_by_id(product_json['id'])
    assert info_json['regular_price'] == updated_price, f"In the Get Product Info the price was not " \
        f"updated as expected. Expected: {updated_price}, Actual: {info_json['regular_price']}"


@pytest.mark.tcid63
def test_update_product_sale_price_trigger_on_sale_flag():
    product_helper = ProductHelper()

    # create simple product
    # generate some data
    payload = dict()
    payload['name'] = generate_random_string(10)
    payload['type'] = 'simple'
    payload['regular_price'] = "9.99"
    product_json = product_helper.create_product(payload=payload)

    updated_sale_price = "4.25"

    # make call to update
    update_json = product_helper.update_product(product_json['id'], {'sale_price': updated_sale_price})
    assert update_json['sale_price'] == updated_sale_price, f"In the Update Product responce the price was not " \
        f"updated as expected. Expected: {updated_sale_price}, Actual: {update_json['sale_price']}"
    assert update_json['on_sale'] == True, f"The on_sale flag was not updated to TRUE"

    # verify response
    info_json = product_helper.get_product_by_id(product_json['id'])
    assert info_json['sale_price'] == updated_sale_price, f"In the Get Product Info the price was not " \
        f"updated as expected. Expected: {updated_sale_price}, Actual: {info_json['sale_price']}"
    assert info_json['on_sale'] == True, f"The on_sale flag was not updated to TRUE"


@pytest.mark.tcid64
def test_remove_product_sale_price_trigger_on_sale_flag():
    product_helper = ProductHelper()

    # create simple product
    # generate some data
    payload = dict()
    payload['name'] = generate_random_string(10)
    payload['type'] = 'simple'
    payload['regular_price'] = "9.99"
    payload['sale_price'] = "8.25"
    product_json = product_helper.create_product(payload=payload)

    updated_sale_price = ""

    # make call to update
    update_json = product_helper.update_product(product_json['id'], {'sale_price': updated_sale_price})
    assert update_json['sale_price'] == updated_sale_price, f"In the Update Product responce the price was not " \
        f"updated as expected. Expected: {updated_sale_price}, Actual: {update_json['sale_price']}"
    assert update_json['on_sale'] == False, f"The on_sale flag was not updated to FALSE"

    import pdb; pdb.set_trace()

    # verify response
    info_json = product_helper.get_product_by_id(product_json['id'])
    assert info_json['sale_price'] == updated_sale_price, f"In the Get Product Info the price was not " \
        f"updated as expected. Expected: {updated_sale_price}, Actual: {info_json['sale_price']}"
    assert info_json['on_sale'] == False, f"The on_sale flag was not updated to FALSE"
