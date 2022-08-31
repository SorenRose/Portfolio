from myStack import *
from random import *

def getTrollDamage():
    return randint(100, 200)
    
def getGoatDamage():
    return randint(0, 100)

def surviveTheCavern(troll, cavern):
    """Returns the surviving goats in the cavern (stack), if any"""
    
    print("STAGE 1: BATTLE WITH THE TROLL...")
        
    # push the goats into the cavern
    print("The troll blocks the cavern exit!")
    print("The goats must fight their way out in order to survive...")
    while not emptyStack(cavern) and troll.hitPoints > 0:
        goat = top(cavern)
        cavern = pop(cavern)
        print(goat.name, "prepares to do battle with the troll...")
        while troll.hitPoints > 0 and goat.hitPoints > 0:
            # goat strikes damage first
            print("\tTroll hit points:", troll.hitPoints, 
                ",", goat.name, "hit points:", goat.hitPoints)
            dmgToTroll = getGoatDamage()
            troll.hitPoints -= dmgToTroll
            print("\t" + goat.name, "does", dmgToTroll, "to troll")
            if (troll.hitPoints > 0):
                dmgToGoat = getTrollDamage()
                goat.hitPoints -= dmgToGoat 
                print("\tTroll does", dmgToGoat, "to goat")
                
        if troll.hitPoints <= 0:
            print(goat.name, "has defeated the Troll!")
            # put them back on the stack
            cavern = push(cavern, goat)
        else:
            print(goat.name, "has fallen!")
                
    # did anyone survive?
    print(str(size(cavern)), "goat/s survived the dark cavern...")
    return cavern
