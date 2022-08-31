"""
Created by: Amanda Ramos 10/17/2015
"""

from ranHW.rit_lib import *
from myQueue import *
from myNode import *

class DequeViaQueue(struct):
    _slots=((Queue,"Queue"))

def createQueue():
    return DequeViaQueue(deque)

def enqueue_front():

def dequeue_front():

def enqueue_back(deque, newElement):
    node=Node(deque,newElement)
    if emptyDeque(deque):
        deque.queue.front=node
    else:
        deque.queue.back.next=node
    deque.queue.back=node
    deque.queue.size+=1

def dequeue_back():
    if emptyDeque(deque):
        raise IndexError("Dequeue on Empty Deque")
    deque.queue.front=deque.queue.front.next
    if emptyDeque(deque):
        deque.queue.back=EMPTY_NODE
    deque.queue.size-=1

def front():
    if emptyDeque(deque):
        raise IndexError("Front on Empty Deque")
    return deque.queue.front.data
def back():
    if emptyDeque(deque):
        raise IndexError("Back on Empty Deque")
    return deque.queue.back.data

def emptyDeque(deque):
    return deque.queue.front==EMPTY_NODE

def size():
    return deque.queue.size