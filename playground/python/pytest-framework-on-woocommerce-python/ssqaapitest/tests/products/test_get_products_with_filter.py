
import pytest
from datetime import datetime, timedelta
from src.helpers.product_helper import ProductHelper
from src.dao.products_dao import ProductsDAO

pytestmark = [pytest.mark.products]

@pytest.mark.regression
class TestListProductsWithFilter(object):

    @pytest.mark.tcid51
    def test_list_products_with_filters_after(self):

        # create data
        x_days_from_today = 30
        _after_created_date = datetime.now().replace(microsecond=0) - timedelta(days=x_days_from_today)
        after_created_date = _after_created_date.isoformat()

        # make the call
        payload = dict()
        payload['after'] = after_created_date
        #payload['per_page'] = "100"
        products_rs = ProductHelper().get_products_with_filter(payload=payload)

        assert products_rs, f"Empty response for 'list products with filter'."

        # get data from db
        products_db = ProductsDAO().get_products_after_date(after_created_date)

        # verify response
        assert len(products_rs) == len(products_db), "The records count if different between" \
            f"database and api with filter after date. Date: {after_created_date}." \
            f"Api: {len(products_rs)}, Database: {len(products_db)}"

        ids_in_api = [i['id'] for i in products_rs]
        ids_in_db = [i['ID'] for i in products_db]

        ids_diff = list(set(ids_in_api) - set(ids_in_db))
        assert not ids_diff, f"Product ids mismatch from db to API response."

