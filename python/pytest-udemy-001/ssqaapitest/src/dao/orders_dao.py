from src.utilities.dbUtility import DBUtility


class OrdersDao(object):

    def __init__(self):
        self.db_helper = DBUtility()

    def get_order_by_id(self, order_id):
        sql = f"SELECT * FROM wordpress.wp_woocommerce_order_items WHERE order_id = {order_id}"
        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql
