import sys
import os

sys.path.append(os.path.dirname(os.path.realpath(__file__)) + "../../src")
print(sys.path)
from src.helpers.customers_helper import CustomerHelper
from src.utilities.genericUtilities import generate_random_email_and_password
from src.dao.customers_dao import CustomersDAO
import pytest
import logging as logger


@pytest.mark.tcid29
def test_create_customer_only_email_password():
    logger.info("TEST: Create new customer with email and password only.")

    rand_info = generate_random_email_and_password()
    logger.info(rand_info)
    email = rand_info['email']
    password = rand_info['password']

    # create payload
    # replaced by CustomerHelper class
    # payload = {'email': email, 'password': password}

    # make the call
    cust_obj = CustomerHelper()
    cust_api_info = cust_obj.create_customer(email=email, password=password)

    # verify status code of the call
    # taken care of in requestsUtility

    # verify email in the response
    assert cust_api_info['email'] == email, f"Create customer api return wrong email. Expected email: {email}"
    assert cust_api_info['first_name'] == '', f"Create customer api returned value for first_name, " \
                                              f"but it should be empty"

    # verify customer is created in database
    cust_dao = CustomersDAO()
    cust_info = cust_dao.get_customers_by_email(email)

    import pdb;
    pdb.set_trace()
