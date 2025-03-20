import pytest


@pytest.fixture(scope='module')
def my_setup():
    print("")
    print(">>>> MY SETUP <<<<<")

    return {'id': 20, 'name': 'Rocket'}

def test_login_page_valid_user(my_setup):
    print("Login with valid user")
    print("Fucntion: aaaaaaa")
    print("Name: {}".format(my_setup.get('name')))

def test_login_page_wrong_password():
    print("Login with wrong password")
    print("Function: bbbbbbbb")