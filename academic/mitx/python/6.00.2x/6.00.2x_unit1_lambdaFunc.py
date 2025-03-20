f1 = lambda x: x
print(f1(3))

f2 = lambda x,y: x+y
print(f2(2,3))
print(f2('Ana', 'Bell'))

f3 = lambda x,y: 'factor' if (x%y == 0) else 'not factor'
print(f3(3,4))