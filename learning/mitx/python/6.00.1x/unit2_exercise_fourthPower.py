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

# Write an iterative function iterPower(base, exp) that calculates the exponential  by simply using successive multiplication. For example, iterPower(base, exp) should compute  by multiplying base times itself exp times. Write such a function below.
# This function should take in two values - base can be a float or an integer; exp will be an integer  0. It should return one numerical value. Your code must be iterative - use of the ** operator is not allowed.

def iterPower(base, exp):
    '''
    base: int or float.
    exp: int >= 0
 
    returns: int or float, base^exp
    '''
    if exp == 1:
        return base
    else:
        return base * iterPower(base, exp - 1)
    
# Write an iterative function, gcdIter(a, b), that implements this idea. One easy way to do this is to begin with a test value equal to the smaller of the two input arguments, and iteratively reduce this test value by 1 until you either reach a case where the test divides both a and b without remainder, or you reach 1.

def gcdIter(a, b):
    '''
    a, b: positive integers
    
    returns: a positive integer, the greatest common divisor of a & b.
    '''
    highest = a if a > b else b
    for num in range(highest + 1, 2, -1):
        if a % num == 0 and b % num == 0:
            return num

# Write a function gcdRecur(a, b) that implements this idea recursively. This function takes in two positive integers and returns one integer.    

def gcdRecur(a, b):
    '''
    a, b: positive integers
    
    returns: a positive integer, the greatest common divisor of a & b.
    '''
    if b == 0:
        return a
    else:
        return gcdRecur(b, a % b)

# Implement the function isIn(char, aStr) which implements the above idea recursively to test if char is in aStr. char will be a single character and aStr will be a string that is in alphabetical order. The function should return a boolean value.

def isIn(char, aStr):
    '''
    char: a single character
    aStr: an alphabetized string
    
    returns: True if char is in aStr; False otherwise
    '''
    stringSize = len(aStr)
    position = int(stringSize/2)
    if stringSize == 0:
        return False

    if char == aStr[position]:
        return True
    elif stringSize == 1:
        return False
    elif char < aStr[position]:
        return isIn(char, aStr[:position])
    elif char > aStr[position]:
        return isIn(char, aStr[position:])
    
    
print(isIn('a', "abcdefghi"))
print(isIn('i', "abcdefghi"))
print(isIn('z', "abcdefghi"))
print(isIn('a', ""))

# Write a function called polysum that takes 2 arguments, n and s. This function should sum the area and square of the perimeter of the regular polygon. The function returns the sum, rounded to 4 decimal places.

import math
def polysum(n, s):
    return round(((0.25 * n * (s**2))/(math.tan(math.pi/n))) + (n*s)**2, 4)
