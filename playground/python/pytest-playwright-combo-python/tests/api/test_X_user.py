import pytest


@pytest.mark.api
@pytest.mark.parametrize("endpoint,expected_code", [
    ("users", 200),
    ("status", 200),
    ("invalid", 404)
])
def test_api_endpoints(api_client, endpoint, expected_code):
    response = api_client(endpoint, method="GET")
    assert response.status_code == expected_code