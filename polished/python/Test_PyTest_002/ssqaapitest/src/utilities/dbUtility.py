
import pymysql
from src.utilities.credentialsUtility import CredentialsUtility


class DBUtility(object):

    def __init__(self):
        creds_helper = CredentialsUtility()
        self.creds = creds_helper.get_db_credentials()
        self.host = 'localhost'

    def create_connection(self):

        connection = pymysql.connect(host=self.host, user=self.creds['db_user'],
                                     password=self.creds['db_password'], port=3306)
        return connection

    def execute_select(self, sql):
        conn = self.create_connection()

        try:
            cur = conn.cursor(pymysql.cursors.DictCursor)
            cur.execute(sql)
            rs_dict = cur.fetchall()
            cur.close()
        except Exception as ex:
            raise Exception(f"Failed running sql: {sql} \n Error: {str(ex)}")
        finally:
            conn.close()

        return rs_dict

    def execute_sql(self, sql):
        pass