import random

from src.utilities.dbUtility import DBUtility


class ProductsDAO(object):

    def __init__(self):
        self.db_helper = DBUtility()

    def get_random_product_from_db(self, qty=1):
        sql = f"SELECT * FROM wordpress.wp_posts WHERE post_type = 'product' ORDER BY id DESC LIMIT 5000;"
        rs_sql = self.db_helper.execute_select(sql)

        return random.sample(rs_sql, int(qty))

    def get_product_by_id(self, product_id):
        sql = f"SELECT * FROM wordpress.wp_posts WHERE post_type = 'product' AND id = {product_id};"
        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql

    def get_products_after_date(self, date):
        sql = f"SELECT * FROM wordpress.wp_posts WHERE post_type = 'product' AND post_date > '{date}'"
        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql