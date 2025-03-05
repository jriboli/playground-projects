import pytest

pytestmark = [pytest.mark.env]


@pytest.mark.skip
def test_environment_is_qa(env):
    assert env == 'qa'


@pytest.mark.skip
def test_environment_is_dev(env):
    assert env == 'dev'


def test_base_url_is_qa(app_config):
    base_url = app_config.base_url
    assert base_url == "https://myqa-env.com"

