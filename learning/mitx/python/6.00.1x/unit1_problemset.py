# Problem 1
# Assume s is a string of lower case characters.
# Write a program that counts up the number of vowels contained in the string s. Valid vowels are: 'a', 'e', 'i', 'o', and 'u'. For example, if s = 'azcbobobegghakl', your program should print:
s = 'azcbobobegghakl'
vowels = 0
for letter in s:
    if letter == "a" or letter == "e" or letter == "i" or letter == "o" or letter == "u":
        vowels += 1
print(vowels)

# Problem 2
# Assume s is a string of lower case characters.
# Write a program that prints the number of times the string 'bob' occurs in s. For example, if s = 'azcbobobegghakl', then your program should print
s = 'azcbobobegghakl'
numOfBobs = 0
index = 0
for letter in s:
    #print(s[index: index+3])
    if letter == "b" and "bob" in s[index: index+3]:
        numOfBobs += 1
    index += 1
print(numOfBobs)

# Problem 3
# Assume s is a string of lower case characters.
# Write a program that prints the longest substring of s in which the letters occur in alphabetical order. For example, if s = 'azcbobobegghakl', then your program should print
# Longest substring in alphabetical order is: beggh
# In the case of ties, print the first substring. For example, if s = 'abcbcd', then your program should print
# Longest substring in alphabetical order is: abc
# Note: This problem may be challenging. We encourage you to work smart. If you've spent more than a few hours on this problem, we suggest that you move on to a different part of the course. If you have time, come back to this problem after you've had a break and cleared your head.
s = 'azcbobobegghakl'
print("a" < "b")
print("" < "a")
print("b" < "a")
finalAnswer = ""
placeHolder = ""
for letter in s:
    if placeHolder == "" or letter >= placeHolder[len(placeHolder)-1]:
        placeHolder += letter
        if len(placeHolder) > len(finalAnswer):
            finalAnswer = placeHolder
    else:
        placeHolder = letter
print(finalAnswer)