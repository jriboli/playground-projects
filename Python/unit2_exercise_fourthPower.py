# Write a Python function, fourthPower, that takes in one number and returns that value raised to the fourth power.
# You should use the square procedure that you defined in an earlier exercise (you don't need to redefine square in this box; when you call square, the grader will use our definition).
# This function takes in one number and returns one number.


def fourthPower(x):
    '''
    x: int or float.
    '''
    return square(x) * square(x)

# Write a Python function, odd, that takes in one number and returns True when the number is odd and False otherwise.
# You should use the % (mod) operator, not if.
# This function takes in one number and returns a boolean.

def odd(x):
    '''
    x: int

    returns: True if x is odd, False otherwise
    '''
    return x % 2 != 0