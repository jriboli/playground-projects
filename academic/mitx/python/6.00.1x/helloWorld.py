# Operator
print(3*5)
print(- - 4)
print(10/3)
print(2.1 ** 2.0)
print(2.2 * 3.0)

# VARIABLES
a = 3.0
a = a + 1.0

# OPERATIONS
x = float(input("Enter a number for x: "))
y = float(input("Enter a number for y: "))
if x == y:
    print("x and y are equal")
    if y!= 0:
        print("Therefore, x / y is", x/y)
elif x < y:
    print("X is smaller")
else:
    print("Y is smaller")
print("Thanks")

# STRINGS
print("a" + "bc")
print(3 * "bc")
# "3" * "bc" => TypeError: can't multiply sequence by non-int of type 'str'
print("abcd"[2])
print("abcd"[0:2])
print("abcd"[:2])
print("abcd"[2:])
print("hello"[-1])
# print("hello"[len("hello")]) => IndexError: string index out of range

str1 = "hello"
str3 = "world"
str4 = str1 + str3
'hello' == str1

print(str4[1:9])
print(str4[::-1])