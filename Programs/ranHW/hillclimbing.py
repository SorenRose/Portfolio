"""
Program by Amanda Ramos Oct. 9th, 2015
"""

import math

from ranHW.rit_lib import *


class point3D(struct):
    _slots=((float,"x"),(float,"y"),(float,"z"))

def evaluate(x,y):
    return math.sin(x*x)*2-y*math.cos(x*y)-x

def maxZPoint(list):
    zPt=0
    for i in range(len(list)):
        if list[i].z>=list[zPt].z:
            zPt=i
    return zPt

def hillClimb(point,step):
    pointLst=[point]
    pointLst.append(point3D(point.x+step,point.y,evaluate(point.x+step,point.y)))
    pointLst.append(point3D(point.x-step,point.y,evaluate(point.x-step,point.y)))
    pointLst.append(point3D(point.x,point.y+step,evaluate(point.x,point.y+step)))
    pointLst.append(point3D(point.x,point.y-step,evaluate(point.x,point.y-step)))
    return pointLst[maxZPoint(pointLst)]

def maxPoint(point,step):
    for i in range(300):
        temp=hillClimb(point,step)
        if temp.z==point.z:
            return point
        point=temp
    return "Failed to find max point"


def main():
    x=float(input("Enter x value: "))
    y=float(input("Enter y value: "))
    step=float(input("Enter step count: "))
    point=point3D(x,y,evaluate(x,y))
    print(point)
    print(maxPoint(point,step))


main()