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
