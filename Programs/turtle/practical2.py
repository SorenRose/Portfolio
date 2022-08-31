"""
Program by, Amanda Ramos, 9/25/2015,
"""

import turtle

def polyHelper(C,N,S):
    if C==0:
        pass
    else:
        turtle.forward(S)
        turtle.left(360/N)
        polyHelper(C-1,N,S)

def poly(N,S):
    polyHelper(N,N,S)

def nest(N,S):
    if N==0:
        pass
    elif N==1:
        pass
    elif N==2:
        pass
    else:
        turtle.up()
        turtle.forward(2*S)
        turtle.down()
        poly(N,S)
        turtle.up()
        turtle.back(2*S)
        turtle.down()
        if(N>3):
            nest(N-1,S/2)
            turtle.up()
            turtle.back(3*S)
            turtle.down()
            poly(N,S)
            turtle.up()
            turtle.forward(3*S)
            turtle.down()


def main():
    nest(6,100)
    input("Press Enter when finished")
    turtle.bye()

main()

