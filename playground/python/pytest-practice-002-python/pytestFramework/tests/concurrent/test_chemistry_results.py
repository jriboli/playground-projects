import time
import pytest

pytestmark = [pytest.mark.fast]


def test_result_1_completes_as_expected():
    time.sleep(5)
    print("Result 1 has completed")


def test_result_2_completes_as_expected():
    time.sleep(5)
    print("Result 2 has completed")


def test_result_3_completes_as_expected():
    time.sleep(5)
    print("Result 3 has completed")


def test_result_4_completes_as_expected():
    time.sleep(5)
    print("Result 4 has completed")


def test_result_5_completes_as_expected():
    time.sleep(5)
    print("Result 5 has completed")
