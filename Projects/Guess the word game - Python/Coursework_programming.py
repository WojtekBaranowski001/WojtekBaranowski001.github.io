#Basic Game Development
#Project made by Wojtek Baranowski
#Last update: 07/05/2020


import turtle #This comand imports turtle, so the program could be able to draw the ambulance. 

#This function draws body of the ambulance with pen color "dark blue".
def Body():
    t1 = turtle.Turtle()
    t1.ht()
    t1.pencolor("dark blue")
    t1.width(3)
    t1.fd(180)
    t1.rt(90)
    t1.fd(20)
    t1.lt(90)
    t1.fd(50)
    t1.rt(90)
    t1.fd(50)
    t1.rt(90)
    t1.fd(230)
    t1.rt(90)
    t1.fd(70)

#Draw front of the ambulance with pen color "blue".     
def Front1():
    t2 = turtle.Turtle()
    t2.ht()
    t2.pencolor("blue") 
    t2.penup()
    t2.goto(180, -20)
    t2.pendown()
    t2.rt(90)
    t2.fd(50)

#Draw second part of front for ambulance with pen color "dark blue".     
def Front2(): 
    t3 = turtle.Turtle()
    t3.ht() 
    t3.pencolor("dark blue") 
    t3.penup()
    t3.goto(190, -30)
    t3.pendown()
    t3.fd(30)
    t3.rt(90)
    t3.fd(10)
    t3.rt(90)
    t3.fd(30)
    t3.rt(90)
    t3.fd(10)
#Draw the siren on top of the ambulance with pen color "dark blue" and fill it with "red". 
def Siren(): 
    t4 = turtle.Turtle()
    t4.ht()
    t4.pencolor("dark blue") 
    t4.penup()
    t4.goto(90, 0)
    t4.pendown()
    t4.begin_fill()
    t4.fillcolor("red")
    t4.lt(90)
    t4.fd(10)
    t4.rt(90)
    t4.fd(10)
    t4.rt(90)
    t4.fd(10)
    t4.end_fill()
    
#Draw wheel with pen color "blue" and fill it with "blue" is well. 
def Wheel(): 
    t5 = turtle.Turtle()
    t5.ht()
    t5.penup()
    t5.goto(40, -50)
    t5.pendown()
    t5.pencolor("blue")
    t5.begin_fill()
    t5.fillcolor("blue")
    for i in range(15): 
        t5.right(15) 
        t5.forward(10)
        t5.rt(15)
    t5.end_fill()

#Draw second whell with pen color "blue" and fill it with "blue". 
def Wheel2(): 
    t6 = turtle.Turtle()
    t6.ht() 
    t6.penup()
    t6.goto(180, -50)
    t6.pendown()
    t6.pencolor("blue")
    t6.begin_fill()
    t6.fillcolor("blue")
    for i in range(15): 
        t6.right(15) 
        t6.forward(10)
        t6.rt(15)
    t6.end_fill()

#Draw first part of the cross with pen color "red" and fill it with "red". 
def Cross1(): 
    t7 = turtle.Turtle()
    t7.ht()
    t7.penup()
    t7.goto(100, -10)
    t7.pendown()
    t7.pencolor("red")
    t7.begin_fill()
    t7.fillcolor("red")
    t7.fd(5)
    t7.rt(90)
    t7.fd(30)
    t7.rt(90)
    t7.fd(5)
    t7.rt(90)
    t7.fd(30)
    t7.end_fill()
#Draw second part of the cross with pen color "red" and fill it with "red" is well. 
def Cross2(): 
    t8 = turtle.Turtle()
    t8.ht()
    t8.penup()
    t8.goto(100, -24)
    t8.pendown()
    t8.pencolor("red")
    t8.begin_fill()
    t8.fillcolor("red")
    t8.fd(20)
    t8.rt(90)
    t8.fd(5)
    t8.rt(90)
    t8.fd(35)
    t8.rt(90)
    t8.fd(5)
    t8.rt(90)
    t8.fd(20)
    t8.end_fill()
#This function allow to draw a square around the left tries number, so the numbers don't draw on top of each other. 
def left_tries():
    draw = turtle.Turtle()
    draw.speed(0) 
    draw.pencolor("yellow")
    draw.penup()
    draw.goto(-100, -250)
    draw.pendown()
    draw.fillcolor("yellow")
    draw.begin_fill()
    draw.fd(50)
    draw.lt(90)
    draw.fd(50)
    draw.lt(90)
    draw.fd(50)
    draw.lt(90)
    draw.fd(50)
    draw.end_fill()

#Reset the game function. 
def gamereset():
    print("\nWould you like to play again?")#Print this message for the player 2 if there is no tries left.
    play_again = input("Enter yes or no: ") #Allow user to enter yes or no.
        
    while True:#While Loop
        if play_again == 'yes': #If input from "play again" is yes, restart the game. 
            print("\n\n\n\n")
            turtle.clearscreen() #Clear turtle screen. 
            return main() #Reset the game.
        elif play_again == 'no':#If input from "play_again" is no, finish the program and print "Thank You" message for the user. 
            print("**********************************")
            print("** THANK YOU FOR PLAYING WITH US**")
            print("**********************************")
            turtle.clearscreen() #Clear turtle screen. 
            break
        
        if play_again != 'yes' or play_again != 'no': #If play_again input is not yes or no. Print error message and let user type in again (Loop until the the input is yes or no)
            print("[!] Invalid input. Please enter yes or no.")
            play_again = input("\nEnter yes or no: ")
        else: #Else break the loop. 
            break 

#Main function 
def main():
  turtle.bgcolor("yellow") #Set background color of turtle.
  turtle.setup(850, 750, 0, 0) #Set screen size of the turtle. 
  word = [] #Variable for word.
  right_guess = [] #Variable for right guesses. 
  wrong_guess = [] #Variable for wrong guesses. 
  tries = 8  #Set number of tries to 8 
  print("*************************") 
  print("** WELCOME TO THE GAME **") #Welcome statement, so whenever user runs the program. 
  print("*************************")
  print("\n----- PLAYER 1 -----")    #Display player 1 
  word = input("Enter your word: ")  #Display the message and allow user to enter a word.
  
  while True: 
      if word == "": #If statement for the word. So, whenever first player doesn't enter a word an error message will pop up and ask user to enter the word again. 
        print("[!] You can't leave it blank.")
        word = input("\nEnter your word: ")
      elif len(word) < 3: #Else if lenght of the word is < 3. Error message will pop up and usk user to enter the word again, until it's longer than three letters. 
          print("[!] You word have to contain at minimum 3 letters.")
          word = input("\nEnter your word: ")
      else: #Else, break the loop. 
          break 
    
  while tries > 0: #While loop 

    player1_word = ""
    for letter in word: #For each letter in the word(player 1) 
        if letter in right_guess: #If letter is in variable right_guess. 
            player1_word = player1_word + letter #Program will show letter and replace rest of the word with *. 
        else: #Else statement 
            player1_word = player1_word + "*" #Else if player 2 doesn't guess the letter it will print the word with * and leave the right guesses.
               

    if player1_word == word: #If player1_word which are * is equal to the first player secret word. Program will print "You won!" message in the turtle and ask users
        you_win = turtle.Turtle() #if they want to play again. 
        you_win.penup()
        you_win.goto(-100, 200)
        you_win.pendown()
        you_win.ht() 
        you_win.write("Congratulation! You win!", font = ("Arial", 28, "italic")) #Display you win message in turtle. 
        print("YOU WON!") #Display you win message in the program screen. 
        print("The word was:", word) #Display player 1 word. 
        print("\n\n") 
        return gamereset() #Reset the game function (asking asking users if they want to play again).
        break
    
     

    print("\n") 
    print("----- PLAYER 2 -----") #Display message for player 2.
    print(player1_word)#Print player 1 word but insted of letter it will be *. For example, player 1 word is "hello" it will print it as "*****".
  
    
    guess = input("Enter your letter: ") #Allow user to guess the letter.

    if guess == "": #If player 2 input it's blank, an error message will pop up and ask user to enter letter again. 
        print("[!] Please enter your letter.")
        guess = input("\nEnter your letter: ")
        
    elif len(guess) != 1: #If player 2 input it's not, an error message will pop up and ask user to enter letter again. 
        print("[!] Please enter one letter at the time.")
        guess = input("\nEnter your letter: ")

            
    if guess in right_guess or guess in wrong_guess: #If player 2 guess is in variable called right_guess or variable called wrong_guess, program will display an error message. 
        print("[!] You already guessed this leter:", guess)
    elif guess in word: #Else if guess is in the player 1 word. 
        print("Good job, keep guessing.")
        right_guess.append(guess)
        print("Wrong guesses: ", wrong_guess) 
    else: #Else turtle will display message that guess is not in the word it will show an error message and take away one of the tries. 
        print("[!] This letter is not in the word.")
        wrong_guess.append(guess)
        print("Wrong guesses:", wrong_guess) #Print wrong guesses even if the letter is correct. 
        tries = tries - 1 #Calculation for tries. 
        turtle_tries = turtle.Turtle()
        turtle_tries.ht() 
        turtle_tries.penup()
        turtle_tries.goto(-250, -250)
        turtle_tries.write("Tries left:", font = ("Arial", 28, "normal")) #Print how many left tries player 2 have in the turtle. 
        print("You have", tries, "tries left") #Print how many left of tries player 2 have.
    
    
    if tries == 7: #If tries is 7, draw the function called Body(). 
        Body()
        turtle_7 = turtle.Turtle()
        turtle_7.ht() 
        turtle_7.penup()
        turtle_7.goto(-80, -250)
        turtle_7.pendown()
        turtle_7.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 6: #If tries is 6, draw the function called Front1(). 
        Front1()
        left_tries()
        turtle_6 = turtle.Turtle()
        turtle_6.ht() 
        turtle_6.penup()
        turtle_6.goto(-80, -250)
        turtle_6.pendown()
        turtle_6.write(tries, font = ("Arial", 28, "normal")) 
         
    elif tries == 5: #If tries is 5, draw the function called Front2().
        Front2()
        left_tries()
        turtle_5 = turtle.Turtle()
        turtle_5.ht() 
        turtle_5.penup()
        turtle_5.goto(-80, -250)
        turtle_5.pendown()
        turtle_5.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 4: #If tries is 4, draw the function called Siren().
        Siren()
        left_tries()
        turtle_4 = turtle.Turtle()
        turtle_4.ht() 
        turtle_4.penup()
        turtle_4.goto(-80, -250)
        turtle_4.pendown()
        turtle_4.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 3: #If tries is 3, draw the function called Wheel(). 
        Wheel()
        left_tries()
        turtle_3 = turtle.Turtle()
        turtle_3.ht() 
        turtle_3.penup()
        turtle_3.goto(-80, -250)
        turtle_3.pendown()
        turtle_3.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 2: #If tries is 2, draw the function called Wheel2(). 
        Wheel2()
        left_tries()
        turtle_2 = turtle.Turtle()
        turtle_2.ht()
        turtle_2.penup()
        turtle_2.goto(-80, -250)
        turtle_2.pendown()
        turtle_2.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 1: #If tries is 1, draw the function called Cross1(). 
        Cross1()
        left_tries()
        turtle_1 = turtle.Turtle()
        turtle_1.ht()
        turtle_1.penup()
        turtle_1.goto(-80, -250)
        turtle_1.pendown()
        turtle_1.write(tries, font = ("Arial", 28, "normal")) 
    elif tries == 0: #If tries is 0, draw the function called Cross2() and display "You lost" message in the turtle. 
        Cross2()
        left_tries()
        print("YOU LOST!") #Display you lost message if tries are 0. 
        print("The word you were looking for was", word) #Display word if player 2 have no tries left. 
        turtle_0 = turtle.Turtle()
        turtle_0.ht()
        turtle_0.penup()
        turtle_0.goto(-80, -250)
        turtle_0.pendown()
        turtle_0.write(tries, font = ("Arial", 28, "normal")) 
        you_lost = turtle.Turtle()
        you_lost.penup()
        you_lost.goto(0, 200)
        you_lost.pendown()
        you_lost.write("YOU LOST!", font = ("Arial", 30, "italic")) 
        you_lost.ht()
        return gamereset() #Function to ask players if they want to play again. 

        
   
    
    
main() #Main function. 

