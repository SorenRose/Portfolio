"""
Program by, Amanda Ramos, 8/29/2015,
"""
import turtle
import math

def drawB():
    """
    drawTriangle draws the letter B
    """
    turtle.forward(10)
    turtle.left(30)
    turtle.forward(10)
    turtle.left(120)
    turtle.forward(10)
    turtle.left(30)
    turtle.forward(10)
    turtle.left(180)
    turtle.forward(10)
    turtle.left(30)
    turtle.forward(10)
    turtle.left(120)
    turtle.forward(10)
    turtle.left(30)
    turtle.forward(10)
    turtle.left(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.up()
    turtle.forward(20)

def drawA():
    turtle.left(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(10)
    turtle.right(90)
    turtle.forward(20)
    turtle.right(180)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(10)
    turtle.left(90)

def drawN():
    turtle.left(90)
    turtle.forward(20)
    turtle.right(135)
    turtle.forward(math.sqrt(800))
    turtle.left(135)
    turtle.forward(20)
    turtle.right(180)
    turtle.forward(20)
    turtle.left(90)

def drawR():
    turtle.left(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(10)
    turtle.right(90)
    turtle.forward(20)
    turtle.right(225)
    turtle.forward(13)
    turtle.up()
    turtle.left(45)
    turtle.forward(10)

def drawM():
    turtle.left(90)
    turtle.forward(20)
    turtle.right(150)
    turtle.forward(20)
    turtle.left(120)
    turtle.forward(20)
    turtle.right(150)
    turtle.forward(20)
    turtle.left(90)

def space():
    turtle.up()
    turtle.forward(20)

def drawC():
    turtle.forward(20)
    turtle.right(180)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(20)
    turtle.up()
    turtle.right(90)
    turtle.forward(20)
    turtle.left(90)

def drawS():
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(10)
    turtle.left(90)
    turtle.forward(20)
    turtle.right(90)
    turtle.forward(10)
    turtle.right(90)
    turtle.forward(20)
    turtle.up()
    turtle.right(90)
    turtle.forward(20)
    turtle.left(90)

def drawI():
    turtle.forward(20)
    turtle.right(180)
    turtle.forward(10)
    turtle.right(90)
    turtle.forward(20)
    turtle.left(90)
    turtle.forward(10)
    turtle.right(180)
    turtle.forward(20)
    turtle.up()
    turtle.right(90)
    turtle.forward(20)
    turtle.left(90)

def pickUpPen():
    """
    picks up pen and moves it to the right corner and moves the pen over for the next character
    """
    turtle.up()
    turtle.forward(10)
    turtle.down()

def main():
    """
    main program draws the letter and picks up the pen from the list of code below
    """
    drawB()
    pickUpPen()
    drawA()
    pickUpPen()
    drawN()
    pickUpPen()
    drawA()
    pickUpPen()
    drawN()
    pickUpPen()
    drawA()
    pickUpPen()
    drawR()
    pickUpPen()
    drawA()
    pickUpPen()
    drawM()
    pickUpPen()
    drawA()
    pickUpPen()
    space()
    pickUpPen()
    drawC()
    pickUpPen()
    drawS()
    pickUpPen()
    space()
    pickUpPen()
    drawI()
    pickUpPen()

    input('Hit enter to close...')

if __name__ == '__main__':
    main()

