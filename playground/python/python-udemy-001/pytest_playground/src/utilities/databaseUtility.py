import pymysql
import os
from src.utilities.credentialsUtility import CredentialsUtility
from src.configs.hosts_config import DB_HOSTS


class DBUtility(object):

    def __init__(self):
        self.creds = CredentialsUtility().get_db_credentials()
        self.env = os.environ.get('ENV', 'test')
        self.db_host = DB_HOSTS[self.env]['endpoint']
        self.db_port = DB_HOSTS[self.env]['port']


    def create_connection(self):

        connection = pymysql.connect(host=self.db_host, user=self.creds['db_user'],
                                     password=self.creds['db_password'], port=self.db_port)
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