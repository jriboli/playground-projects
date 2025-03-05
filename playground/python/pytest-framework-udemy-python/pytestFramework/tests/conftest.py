import json

from pytest import fixture
from selenium import webdriver
from config import Config
from pathlib import Path

def pytest_addoption(parser):
    parser.addoption(
        "--env",
        action="store",
        #default=""
        help="Environment to run test against"
    )


@fixture(scope='session')
def env(request):
    return request.config.getoption('--env')


@fixture(scope='session')
def app_config(env):
    cfg = Config(env)
    return cfg


@fixture(params=[webdriver.Chrome, webdriver.Firefox, webdriver.edge])
def browser(request):
    driver = request.param
    drvr = driver()
    yield drvr
    drvr.quit()


# Define the path relative to the current file's location
data_path = Path(__file__).parent / 'parameterizing' / 'testdata.json'


def load_test_data(path):
    with open(path) as data_file:
        data = json.load(data_file)
        return data


@fixture(params=load_test_data(data_path))
def test_data(request):
    data = request.param
    return data
