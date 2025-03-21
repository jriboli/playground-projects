# test_api.py

from fastapi.testclient import TestClient
from main import app
from unittest import mock

client = TestClient(app)

def test_get_cheatsheets():
    response = client.get("/cheatsheets")
    assert response.status_code == 200
    assert isinstance(response.json(), list)

# Using Mock
def test_fetch_weather():
    with mock.patch('api_client.requests.get') as mock_get:
        # Mock the response from the API
        mock_get.return_value.json.return_value = {"temperature": 22, "city": "London"}
        
        # Run the function under test
        result = fetch_weather("London")
        
        # Assert the mock was called and the function returns the correct result
        mock_get.assert_called_with("https://weatherapi.com/London")
        assert result == {"temperature": 22, "city": "London"}