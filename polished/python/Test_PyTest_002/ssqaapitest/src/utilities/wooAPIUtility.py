
import os
import logging as logger

from src.utilities.credentialsUtility import CredentialsUtility
from src.configs.hosts_config import WOO_HOSTS
from woocommerce import API

class WooAPIUtility(object):

    def __init__(self):

        wc_creds = CredentialsUtility.get_wc_api_keys()

        self.env = os.environ.get('ENV', 'test')
        self.base_url = WOO_HOSTS[self.env]

        self.wcapi = API(
            url=self.base_url,
            consumer_key=wc_creds['wc_key'],
            consumer_secret=wc_creds['wc_secret'],
            version="wc/v3"
        )

    def assert_status_code(self):
        assert self.rs_status_code == self.expected_status_code, f"Bad Status Code." \
            f"Expected: {self.expected_status_code}, Actual: {self.rs_status_code}"

    def get(self, wc_endpoint, params=None, expected_status_code=200):

        rs_api = self.wcapi.get(wc_endpoint, params=params)
        self.rs_status_code = rs_api.status_code
        self.expected_status_code = expected_status_code
        self.rs_json = rs_api.json()
        logger.debug(f"API GET response: {self.rs_json}")

        self.assert_status_code()

        return self.rs_json

    def post(self, wc_endpoint, params=None, expected_status_code=200):

        rs_api = self.wcapi.post(wc_endpoint, data=params)
        self.rs_status_code = rs_api.status_code
        self.expected_status_code = expected_status_code
        self.rs_json = rs_api.json()
        logger.debug(f"API POST response: {self.rs_json}")

        self.assert_status_code()

        return self.rs_json

    def put(self, wc_endpoint, params=None, expected_status_code=200):

        rs_api = self.wcapi.put(wc_endpoint, data=params)
        self.rs_status_code = rs_api.status_code
        self.expected_status_code = expected_status_code
        self.rs_json = rs_api.json()
        logger.debug(f"API PUT response: {self.rs_json}")

        self.assert_status_code()

        return self.rs_json
