"""
Simulation of the Billy Goat's Gruff
file: gruff.py
author: Arthur Nunes-Harwitt
"""

from random import *
from goatsTroll import *
from cavern import *
from bridge import *

    
def welcome():
    print("Billy Goats Gruff v1.0")
    print("-------------")
    print("Welcome to the field.  Goats yearn for the berries on the hills.")

def endingCredits(troll, goats):
    if troll.hitPoints > 0:
        print("The troll, with", troll.hitPoints,
            "hit points remaining, claims victory!")
    elif goats.size > 0:
        print(str(goats.size), "goats reach the berries and eat their fill!")
        while not emptyQueue(goats):
            goat = front(goats)
            dequeue(goats)
            print(goat.name, ", with", goat.hitPoints,
                "hit points remaining, eats berries!")
        print("The goats live happily ever after!")
    print("THE END.")
    
def main():
    welcome()
    # spawn the troll!
    troll = createTroll()
    print("The troll spawns somewhere in front of the bridge with", 
        troll.hitPoints, "hit points...")
        
    # create the goats and push them into the cavern
    numGoats = int(input("How many goats? "))
    cavern = EMPTY_NODE
    for id in range(1, numGoats+1):
        goat = createGoat(id)
        print("Goat", goat.name, "with", goat.hitPoints,
            "hit points and", goat.weight, "weight, enters the cavern...")
        cavern = push(cavern, goat)
        
    # get the berries!
    cavern = surviveTheCavern(troll, cavern)
    goats = createQueue()
    if (size(cavern) > 0):
        input("Hit enter to continue...")
        crossTheBridge(cavern, goats)

    endingCredits(troll, goats)
   
main()
