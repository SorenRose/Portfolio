"""
Program by, Amanda Ramos, 9/4/2015,
"""
import turtle
import math

def drawFlake0(segment,size):
    if segment==0:
        pass

def drawBigSquare(segment, size):
    if segment==0:
        return
    color(segment)
    turtle.left(90)
    color(segment)
    turtle.forward(size/2)
    drawSquares(segment-1,size/(2*math.sqrt(2)))
    color(segment)
    turtle.forward(size/2)
    turtle.right(90)
    turtle.forward(size)
    turtle.right(90)
    color(segment)
    turtle.forward(size/2)
    drawSquares(segment-1,size/(2*math.sqrt(2)))
    color(segment)
    turtle.forward(size/2)
    turtle.right(90)
    turtle.forward(size)

def drawSquares(segment,size):
    if segment==0:
        return
    turtle.right(45)
    color(segment)
    turtle.forward(size/2)
    drawSquares(segment-1,size/(2*math.sqrt(2)))
    color(segment)
    turtle.forward(size/2)
    turtle.right(90)
    turtle.forward(size)
    turtle.right(90)
    color(segment)
    turtle.forward(size/2)
    drawSquares(segment-1,size/(2*math.sqrt(2)))
    color(segment)
    turtle.forward(size/2)
    turtle.right(90)
    turtle.forward(size)
    turtle.right(45)

def color(segment):
    if(segment%2==1):
        turtle.color('blue')
    else:
        turtle.color('orange')

def main():

    drawBigSquare(int(input('Enter number here.')),300)
    input('Hit enter to close.')

if __name__ == '__main__':
    main()