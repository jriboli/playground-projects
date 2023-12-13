print("Please think of a number between 0 and 100!")

low = 0
high = 100
userInput = ""

while(userInput != "c"):
    ans = int((high + low)/2)
    userInput = input("Is your secret number " + str(ans) + "? \nEnter 'h' to indicate the guess is too high. Enter 'l' to indicate the guess is too low. Enter 'c' to indicate I guessed correctly. ")

    if userInput == "h":
        high = ans
    elif userInput == "l":
        low = ans
    elif userInput == "c":
        break
    else:
        print("Sorry, I did not understand your input.")
    
print("Game over. Your secret number was:", ans)