"""Program by, Amanda Ramos, 9/3/2015"""

import turtle


def drawFlake0(segment,size):
    if segment==0:
        pass

def drawFlake1(segment, size):
    if segment==0:
        return
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)
    turtle.forward(size)
    drawTips(segment-1,size/2)
    turtle.back(size)
    turtle.right(60)

def drawTips(segment,size):
    if segment==0:
        return
    turtle.left(60)
    turtle.left(60)
    turtle.forward(size/2)
    drawTips(segment-1,size/2)
    turtle.back(size/2)
    turtle.right(60)
    turtle.forward(size/2)
    drawTips(segment-1,size/2)
    turtle.back(size/2)
    turtle.right(60)
    turtle.forward(size/2)
    drawTips(segment-1,size/2)
    turtle.back(size/2)
    turtle.right(60)
    turtle.forward(size/2)
    drawTips(segment-1,size/2)
    turtle.back(size/2)
    turtle.right(60)
    turtle.forward(size/2)
    drawTips(segment-1,size/2)
    turtle.back(size/2)
    turtle.left(60)
    turtle.left(60)

def main():

    drawFlake1(int(input('Enter number here.')),100)
    input('Hit enter to close.')

if __name__ == '__main__':
    main()