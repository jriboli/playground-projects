import os
from src.utilities.envUtility import EnvUtility


class CredentialsUtility(object):

    def __init__(self):
        pass

    @staticmethod
    def get_wc_api_keys():
        env_util = EnvUtility()

        #wc_key = os.environ.get('WC_KEY')
        #wc_secret = os.environ.get('WC_SECRET')
        wc_key = env_util.get_wc_key()
        wc_secret = env_util.get_env_value(key='WC_SECRET')

        if not wc_key or not wc_secret:
            raise Exception("The API credentials 'WC_KEY' and 'WC_SECRET' must be in env variable.")
        else:
            return {'wc_key': wc_key, 'wc_secret': wc_secret}

    @staticmethod
    def get_db_credentials():
        env_util = EnvUtility()

        db_user = env_util.get_db_user()
        db_password = env_util.get_env_value(key='DATABASE_PASSWORD')

        if not db_user or not db_password:
            raise Exception("The database credentials 'DB_USER' and 'DB_PASSWORD' must be in env variable.")
        else:
            return {'db_user': db_user, 'db_password': db_password}
