
# PROBLEM SET 1 -- Paying Debt Off in a Year
balance = 42
annualInterestRate = 0.2
monthlyInterestRate = annualInterestRate / 12
monthlyPaymentRate = 0.04

# balance = 484
# annualInterestRate = 0.2
# monthlyInterestRate = annualInterestRate / 12.0
# monthlyPaymentRate = 0.04

def calculateMonthlyBalanceWithMinimumPayment(balance, monthlyInterestRate, monthlyPaymentRate):
    '''
    minimum monthly payment is monthlyPaymentRate * previousBalance
    monthly interest is (previousBalance - monthlyPayment) * monthlyInterestRate

    Args:
        balance(float): previous months balance
        monthlyInterestRate(float): annual rate / 12
        monthlyPaymentRate(float): monthly rate

    Returns:
        remainingBalance (float)
    '''
    payment = monthlyPaymentRate * balance
    remainingUnpaid = balance - payment
    newInterestAmount = monthlyInterestRate * remainingUnpaid

    return round(remainingUnpaid + newInterestAmount, 2)

for months in range(1,13):
    balance = calculateMonthlyBalanceWithMinimumPayment(balance, monthlyInterestRate, monthlyPaymentRate)
    #print("Month", months, "Remaining balance:", balance)

#print("Remaining balance:", balance)


# PROBLEM SET 2 - Paying Debt Off in a Year
# balance = 3329
# annualInterestRate = 0.2
# monthlyInterestRate = annualInterestRate / 12.0

balance = 3926
annualInterestRate = 0.2
monthlyInterestRate = annualInterestRate / 12.0

def calculateMonthlyToPayOfInOneYear(balance, monthlyInterestRate, monthlyPayment):
    '''
    minimum monthly payment is monthlyPaymentRate * previousBalance
    monthly interest is (previousBalance - monthlyPayment) * monthlyInterestRate

    Args:
        balance(float): previous months balance
        monthlyInterestRate(float): annual rate / 12
        monthlyPaymentRate(float): monthly rate

    Returns:
        remainingBalance (float)
    '''
    remainingUnpaid = balance - monthlyPayment
    newInterestAmount = monthlyInterestRate * remainingUnpaid

    return round(remainingUnpaid + newInterestAmount, 2)

payment = 10
foundMinimumPayment = False
while not(foundMinimumPayment):
    tempPlaceHolder = balance
    for months in range(1,13):
        tempPlaceHolder = calculateMonthlyToPayOfInOneYear(tempPlaceHolder, monthlyInterestRate, payment)

    if tempPlaceHolder <= 0:
        foundMinimumPayment = True 
    else:
        payment += 10

print(payment)


# PROBLEM SET 3 - Use Bisection Search to Make the Program faster
# balance = 320000
# annualInterestRate = 0.2
# monthlyInterestRate = annualInterestRate / 12.0
# Answer: 29157.09

balance = 999999
annualInterestRate = 0.18
monthlyInterestRate = annualInterestRate / 12.0
# Answer: 90325.03

def calculateMonthlyToPayOfInOneYear(balance, monthlyInterestRate, monthlyPayment):
    '''
    minimum monthly payment is monthlyPaymentRate * previousBalance
    monthly interest is (previousBalance - monthlyPayment) * monthlyInterestRate

    Args:
        balance(float): previous months balance
        monthlyInterestRate(float): annual rate / 12
        monthlyPaymentRate(float): monthly rate

    Returns:
        remainingBalance (float)
    '''
    remainingUnpaid = balance - monthlyPayment
    newInterestAmount = monthlyInterestRate * remainingUnpaid

    return round(remainingUnpaid + newInterestAmount, 2)

def calculateMonthlyPaymentWithBisection(payment, balance):
    tempPlaceHolder = balance
    for months in range(1,13):
        tempPlaceHolder = calculateMonthlyToPayOfInOneYear(tempPlaceHolder, monthlyInterestRate, payment)

    if tempPlaceHolder < payment:
        return payment
    elif  


payment = 10
foundMinimumPayment = False
while not(foundMinimumPayment):
    tempPlaceHolder = balance
    for months in range(1,13):
        tempPlaceHolder = calculateMonthlyToPayOfInOneYear(tempPlaceHolder, monthlyInterestRate, payment)

    if tempPlaceHolder <= 0:
        foundMinimumPayment = True 
    else:
        payment += 10

print(payment)

