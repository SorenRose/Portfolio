"""
Program by, Amanda Ramos, 9/14/2015,
"""
import turtle
import random
import math

def init():
    turtle.screensize(BOUNDING_BOX()*2,BOUNDING_BOX()*2)
    turtle.speed(0)
    turtle.hideturtle()

def BOUNDING_BOX():
    return 200

def drawBOUNDING_BOX():
    turtle.up()
    turtle.setpos(-BOUNDING_BOX(),-BOUNDING_BOX())
    turtle.down()
    turtle.forward(BOUNDING_BOX()*2)
    turtle.left(90)
    turtle.forward(BOUNDING_BOX()*2)
    turtle.left(90)
    turtle.forward(BOUNDING_BOX()*2)
    turtle.left(90)
    turtle.forward(BOUNDING_BOX()*2)
    turtle.up()

def  Arrows(num):
    if num<0 or num>500:
        input('ERROR: Arrows must be between 0-500 inclusive.')
    else:
        return num

def MAX_FIGURES():
    return 500

def MAX_SIZE():
    return 30

def MAX_DISTANCE():
    return 30

def MAX_ANGLE():
    return 30

def TRI_AREA(side):
    return ((side*side)*math.sqrt(3))/4

def drawTriangles():
    turtle.color(random.random(),random.random(),random.random())
    turtle.begin_fill()
    SideLength=random.randint(1,MAX_SIZE())
    turtle.left(90)
    turtle.forward(SideLength/2)
    turtle.right(120)
    turtle.forward(SideLength)
    turtle.right(120)
    turtle.forward(SideLength)
    turtle.right(120)
    turtle.forward(SideLength/2)
    turtle.end_fill()
    turtle.right(90)
    return TRI_AREA(SideLength)

def drawFiguresRec(depth):
    if(depth==0):
        return 0
    else:
        area=drawTriangles()
        turtle.up()
        x,y=turtle.position()
        distance=random.randint(1,MAX_DISTANCE())
        if(x>BOUNDING_BOX()-30 or y>BOUNDING_BOX()-30 or x<-BOUNDING_BOX()+30 or y<-BOUNDING_BOX()+30):
                turtle.left(150)
                turtle.forward(35)
        else:
            turtle.forward(distance)
            turtle.left(random.randint(-MAX_ANGLE(),MAX_ANGLE()))
        turtle.down()
        return area+drawFiguresRec(depth-1)


def drawFiguresIntr(depth):
    area=0
    while depth>0:
        area+=drawTriangles()
        turtle.up()
        x,y=turtle.position()
        distance=random.randint(1,MAX_DISTANCE())
        if(x>BOUNDING_BOX()-30 or y>BOUNDING_BOX()-30 or x<-BOUNDING_BOX()+30 or y<-BOUNDING_BOX()+30):
                turtle.left(180)
                turtle.forward(35)
        else:
            turtle.forward(distance)
            turtle.left(random.randint(-MAX_ANGLE(),MAX_ANGLE()))
        turtle.down()
        depth=depth-1
    return area


def main():
    init()
    drawBOUNDING_BOX()
    turtle.setpos(0,0)
    print('Area of recursive: ', drawFiguresRec(Arrows(int(input("Enter number of triangles: ")))))
    input('Press Enter to continue this program')
    turtle.reset()
    init()
    turtle.setpos(0,0)
    drawBOUNDING_BOX()
    turtle.up()
    turtle.setpos(0,0)
    print('Area of interative: ',drawFiguresIntr(Arrows(int(input("Enter number of triangles: ")))))
    input('Hit enter to close this program')

main()