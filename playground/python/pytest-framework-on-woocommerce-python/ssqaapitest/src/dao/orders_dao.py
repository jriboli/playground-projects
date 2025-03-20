import random
from src.utilities.dbUtility import DBUtility


class OrdersDao(object):

    def __init__(self):
        self.db_helper = DBUtility()

    def get_order_by_id(self, order_id):
        sql = f"SELECT * FROM wordpress.wp_woocommerce_order_items WHERE order_id = {order_id}"
        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql

    def get_random_order(self, qty=1):
        sql = f"SELECT * FROM wordpress.wp_woocommerce_order_items ORDER BY order_id DESC LIMIT 5000;"
        rs_sql = self.db_helper.execute_select(sql)

        return random.sample(rs_sql, int(qty))
