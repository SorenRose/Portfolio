"""
Program by, Amanda Ramos, 8/28/2015,
"""
import turtle

def drawTriangle():
    """
    drawTriangle draws a triangle and makes sure the pen is down at the beginning
    """
    turtle.down()
    turtle.left(60)
    turtle.forward(100)
    turtle.right(120)
    turtle.forward(100)
    turtle.right(120)
    turtle.forward(100)

def pickUpPen():
    """
    picks up pen and moves it to the right corner and turns the pen for next triangle
    """
    turtle.up()
    turtle.left(180)
    turtle.forward(100)
    turtle.right(72)

def main():
    """
    main program draws the triangles and picks up the pen from the list of code below
    """
    drawTriangle()
    pickUpPen()
    drawTriangle()
    pickUpPen()
    drawTriangle()
    pickUpPen()
    drawTriangle()
    pickUpPen()
    drawTriangle()
    pickUpPen()
    input('Hit enter to close...')

if __name__ == '__main__':
    main()
