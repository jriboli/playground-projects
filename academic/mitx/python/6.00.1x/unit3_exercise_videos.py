testString = "hello"

print(testString[-1])

x = (1, 2, (3, 'John', 4), 'Hi')

print(x[2])
print(x[-1])
print(x[0:-1])
print(len(x))
print(2 in x)
print(3 in x)
print(x[0:1])

x.count
x += (5,)

print(x)

def oddTuples(aTup):
    '''
    aTup: a tuple
    
    returns: tuple, every other element of aTup. 
    '''
    resultTup = ()
    for num in range(1, len(aTup)+1):
        if num % 2 != 0 :
            resultTup += (aTup[num - 1],)
            
    return resultTup

print(oddTuples((9,)))

# ----------------------------------------------------------------------------

y = [1, 2, [3, 'John', 4], 'Hi'] 
print(y[0:1])
print(2 in y)
print(3 in y)

# ----------------------------------------------------------------------------

listA = [1, 4, 3, 0]
listB = ['x', 'z', 't', 'q']

print("------------------------------------------------------------")
print(listA)
listA.sort()
listA.insert(0, 100)
listA.remove(3)
listA.append(7)
print(listA)
listA.extend([4, 1, 6, 3, 4])
print(listA.count(4))
print(listA.index(1))
print(listA.pop(4))
listA.reverse()
print(listA)
print(listA + listB)
listB.sort()
listB.pop() 
# listB.remove('a') -- ValueError: list.remove(x): x not in list
print(listB.count('a'))
print(listB)

# ----------------------------------------------------------------------------

aList = [0, 1, 2, 3, 4, 5]
bList = aList
aList[2] = 'hello'
aList == bList

print("------------------------------------------------------------")
print(aList is bList)
print(aList)

cList = [6, 5, 4, 3, 2]
dList = []
for num in cList:
    dList.append(num)
cList == dList

print(cList)
print(cList in dList)
cList[2] = 20
print(cList)
print(dList)

# ----------------------------------------------------------------------------

def applyToEach(L, f):
    for i in range(len(L)):
        L[i] = f(L[i])

testList = [1, -4, 8, -9]

def applyEachTo(L, x):
    result = []
    for i in range(len(L)):
        result.append(L[i](x))
    return result

print("------------------------------------------------------------")

# ----------------------------------------------------------------------------

animals = {'a': 'aardvark', 'b': 'baboon', 'c': 'coati'}

animals['d'] = 'donkey'

print("------------------------------------------------------------")

print(animals)
print(animals.values())

# ----------------------------------------------------------------------------

animals = { 'a': ['aardvark'], 'b': ['baboon'], 'c': ['coati']}

animals['d'] = ['donkey']
animals['d'].append('dog')
animals['d'].append('dingo')

print("------------------------------------------------------------")

print(animals)
print(len(animals))
counter = 0
for e in animals.values():
    print(e)
    counter += len(e)
print(counter)

print(max(animals))

print("------------------------------------------------------------")
aDict = {'a': [15, 2], 'c': [18, 13, 10, 11, 10], 'b': [7, 3, 14, 1, 18, 5, 13, 10, 2, 11], 'd': [18]}

my_dict = {}
for e in aDict:
    my_dict[e] = len(aDict[e])