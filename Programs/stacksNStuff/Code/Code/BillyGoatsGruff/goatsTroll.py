"""
Representation of goats and trolls.
file: goatsTroll.py
author: Arthur Nunes-Harwitt
"""

from random import *

from ranHW.rit_lib import *


def getTrollHitpoints():
    return randint(1000, 2000)
       
class Troll(struct):
    _slots = ((int, 'hitPoints'))
    
def createTroll():
    return Troll(getTrollHitpoints())
    
def getGoatHitPoints():
    return randint(100, 200)
    
def getGoatWeight():
    return randint(50, 300)
        
class Goat(struct):
    _slots = ((str, 'name'), (int, 'hitPoints'), (int, 'weight'))
    
def createGoat(id):
    return Goat("Goat #" + str(id), getGoatHitPoints(), getGoatWeight())

