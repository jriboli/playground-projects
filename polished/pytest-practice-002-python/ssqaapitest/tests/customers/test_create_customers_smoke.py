import pdb
import sys
import os

sys.path.append(os.path.dirname(os.path.realpath(__file__)) + "../../src")
print(sys.path)
from src.helpers.customers_helper import CustomerHelper
from src.utilities.genericUtilities import generate_random_email_and_password
from src.dao.customers_dao import CustomersDAO
from src.utilities.requestsUtility import RequestsUtility
import pytest
import logging as logger


@pytest.mark.customers
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

    id_in_api = cust_api_info['id']
    id_in_db = cust_info[0]['ID']
    assert id_in_api == id_in_db, f"Create customer response 'id' not same as 'ID' in database." \
                                  f"Email: {email}"


@pytest.mark.customers
@pytest.mark.tcid47
def test_create_customer_fail_for_existing_email():
    # get existing email from db
    cust_dao = CustomersDAO()
    existing_customer = cust_dao.get_random_customer_from_db()
    existing_email = existing_customer[0]['user_email']

    # make the call
    requests_utility = RequestsUtility()
    payload = {"email": existing_email, "password": "Password1"}
    create_user_json = requests_utility.post('customers', payload=payload, expected_status_code=400)

    assert create_user_json['code'] == 'registration-error-email-exists', f"Create customer with" \
        f"existing user error 'code' is not correct. Expected: 'registration-error-email-exists' " \
        f"Actualy: {create_user_json['code']}"

    assert create_user_json['message'] == 'An account is already registered with your email address. <a ' \
        f'href="#" class="showlogin">Please log in.</a>', \
        f"Create customer with existing user error 'message' is not correct. " \
        f"Actualy: {create_user_json['message']}"
