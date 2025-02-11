import os
from dotenv import load_dotenv
from pathlib import Path


class CredentialsUtility(object):

    def __init__(self, env_file=None):
        if env_file:
            env_path = Path(env_file)
        else:
            env_path = Path(__file__).resolve().parent.parent.parent / '.env'

        load_dotenv(dotenv_path=env_path)

    def get_wc_api_keys(self):
        wc_key = os.getenv(key='WC_KEY')
        wc_secret = os.getenv(key='WC_SECRET')

        if not wc_key or not wc_secret:
            raise Exception("The API credentials 'WC_KEY' and 'WC_SECRET' must be in env variable.")
        else:
            return {'wc_key': wc_key, 'wc_secret': wc_secret}

    def get_db_credentials(self):
        db_user = os.getenv(key='DATABASE_USER')
        db_password = os.getenv(key='DATABASE_PASSWORD')

        if not db_user or not db_password:
            raise Exception("The database credentials 'DB_USER' and 'DB_PASSWORD' must be in env variable.")
        else:
            return {'db_user': db_user, 'db_password': db_password}
