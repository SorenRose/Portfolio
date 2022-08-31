"""
Program by Amanda Ramos, Oct 20th 2015
"""

from ballPuzzle_Animate import *

def ballSort(ballString):
    canList=[EMPTY_NODE, EMPTY_NODE, EMPTY_NODE]
    Red=0
    Green=1
    Blue=2
    moveCt=0
    for x in ballString:
        canList[Red]=push(canList[Red],x)
    while(not(emptyStack(canList[Red]))):
        if top(canList[Red])=="R" or top(canList[Red])=="G":
            canList[Green]=push(canList[Green],top(canList[Red]))
            canList[Red]=pop(canList[Red])
            animate_move(canList,Red,Green)
            moveCt+=1
        else:
            canList[Blue]=push(canList[Blue],top(canList[Red]))
            canList[Red]=pop(canList[Red])
            animate_move(canList, Red, Blue)
            moveCt+=1
    while(not(emptyStack(canList[Green]))):
        if top(canList[Green])=="R":
            canList[Red]=push(canList[Red],top(canList[Green]))
            canList[Green]=pop(canList[Green])
            animate_move(canList,Green,Red)
            moveCt+=1
        else:
            canList[Blue]=push(canList[Blue],top(canList[Green]))
            canList[Green]=pop(canList[Green])
            animate_move(canList,Green,Blue)
            moveCt+=1
    while top(canList[Blue])=="G":
        canList[Green]=push(canList[Green],top(canList[Blue]))
        canList[Blue]=pop(canList[Blue])
        animate_move(canList,Blue,Green)
        moveCt+=1
    return moveCt

def main():
    ballString=input("Create list of balls using R, G, or B: ")
    animate_init(ballString)
    print("Moves: ",ballSort(ballString))
    input("Press Enter to Exit")
    animate_finish()

main()