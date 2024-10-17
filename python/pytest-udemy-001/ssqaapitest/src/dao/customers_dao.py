
from src.utilities.dbUtility import DBUtility


class CustomersDAO(object):

    def __init__(self):
        self.db_helper = DBUtility()

    def get_customers_by_email(self, email):
        sql = f"SELECT * FROM wordpress.wp_users WHERE user_email = '{email}';"
        rs_sql = self.db_helper.execute_select(sql)

        return rs_sql

