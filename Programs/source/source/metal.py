"""
Author: Sean Strout (sps@cs.rit.edu)
Author: ben k steele (bks@cs.rit.edu)
Author: Amanda Ramos :)

This class represents the types of metal bars that Greedo can
store in his satchel.  Each type of bar is a separate Metal
object.  This module also has routines that work with metals,
e.g. creation, reading from a file, and sorting based on 
various criteria.

Language: Python 3
"""

from ranHW.rit_lib import *            # rit_lib class

class Metal(struct):
    """
    Represents a single metal type, composed of:
    :slot name (str): The name of the metal
    :slot totalBars (int): The total number of bars
    :slot weightPerBar (int): The weight of a single bar
    :slot valuePerBar (int): The value of a single bar
    :slot valuePerWeight (float): The value per weight of the metal
    :slot barsTaken (int): The number of bars added to the satchel
    """
    _slots=((str, "name"),(int, "totalBars"),(int, "weightPerBar"),(int,"valuePerBar"),(float, "valuePerWeight"),(int,"barsTaken"))


def createMetal(name, totalBars, weightPerBar, valuePerBar):
    """
    Create and return a new Metal object.
    :param name (str): The name of the metal
    :param totalBars (int): The total number of bars
    :param weightPerBar (int): The weight of a single bar
    :param valuePerBar (int): The value of a single bar
    :return: A newly initialized Metal object
    :rtype: Metal
    """
    return Metal(name,totalBars,weightPerBar,valuePerBar,weightPerBar/valuePerBar,0)

def readMetals(fileName):
    """
    Read the metals from a file whose format is:
        metalName totalBars weightPerBar valuePerBar
    :param fileName (str): The name of the file
    :return: A list of Metal objects
    :rtype: list
    """
    lst=[]
    metals=open(fileName)
    for i in metals:
        i=i.split()
        lst.append(createMetal(i[0],int(i[1]),int(i[2]),int(i[3])))
    return lst


def sortMetalsByValuePerBar(metals):
    """
    Sort the metals by value per bar using insertion sort.  The list of
    metals is modified in place to be ordered by value per bar.
    :param metals (list of Metal): The list of metals
    :return: None
    :rtype: NoneType
    """
    for m in range(len(metals)):
        for v in range(m):
            if metals[m].valuePerBar>metals[v].valuePerBar:
                temp=metals[v]
                metals[v]=metals[m]
                metals[m]=temp

def sortMetalsByValuePerWeight(metals):
    """
    Sort the metals by value per weight using insertion sort.  The list of
    metals is modified in place to be ordered by value per weight.
    :param metals (list of Metal): The list of metals
    :return: None
    :rtype: NoneType
    """
    for m in range(len(metals)):
        for v in range(m):
            if metals[m].valuePerWeight>metals[v].valuePerWeight:
                temp=metals[v]
                metals[v]=metals[m]
                metals[m]=temp

def printMetals(metals):
    """
    Display the metals to standard output.
    :param metals (list of Metal): The list of metals
    :return: None
    :rtype: NoneType
    """
    for m in metals:
        print("\t",m)