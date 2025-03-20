num = 5
if num > 2:
    print(num)
    num -= 1
print(num)

print("################################")

num = 0
while num <= 5:
    print(num)
    num += 1

print("Outside of loop")
print(num) 

print("################################")

# INFINITE LOOP -------------------------------------------------
# numberOfLoops = 0
# numberOfApples = 2
# while numberOfLoops < 10:
#     numberOfApples *= 2
#     numberOfApples += numberOfLoops
#     numberOfLoops -= 1
# print("Number of apples: " + str(numberOfApples))

# print("################################")

num = 10
while num > 3:
    num -= 1
    print(num) 

print("################################")

num = 10
while True:
    if num < 7:
        print('Breaking out of loop')
        break
    print(num)
    num -= 1
print('Outside of loop')

print("################################")

# INFINITE LOOP -------------------------------------------------
# num = 100
# while not False:
#     if num < 0:
#         break
# print('num is: ' + str(num)) 

# print("################################")


counter = 0
while(counter < 10):
    counter += 2
    print(counter)
print("Goodbye!")

print("Hello!")
counter = 10
while(counter > 0):
    print(counter)
    counter -= 2

# END not defined
# counter = 1
# sumTotal = 0
# while(counter <= end):
#     sumTotal += counter
#     counter += 1
# print(sumTotal)

for letter in 'hola':
    print(letter)  