def is_even(i):
    """
    Input: i, a positive int
    Output: 
    """
    return i%2 == 0

print(is_even(3))

# Exercise 1
# Write a Python function, square, that takes in one number and returns the square of that number.
# This function takes in one number and returns one number.

def square(x):
    '''
    x: int or float.
    '''
    return x * x

print(square(5))

# Exercise 3

def a(x, y, z):
     if x:
         return y
     else:
         return z

def b(q, r):
    return a(q>r, q, r)

print(a(False, 2, 3))
print(b(3, 2))
print(a(3>2, a, b))
# print(b(a, b)) -- TypeError: '>' not supported between instances of 'function' and 'function'

# Exercise 4
a = 10
def f(x):
    return x + a
a = 3
print(f(1))

x = 12
def g(x):
    x = x + 1
    def h(y):
        return x + y
    return h(6)
print(g(x))

# Exercise 5
def foo(x, y = 5):
    def bar(x):
        return x + 1
    return bar(y * 2)
print(foo(3))