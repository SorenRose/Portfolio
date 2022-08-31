"""
Queue implementation built on top of nodes.
file: myQueue.py
author: Sean Strout
"""

from myNode import *

class Queue(struct):
    _slots = ( ((EmptyNode, Node), 'front'), ((EmptyNode, Node), 'back'), (int, 'size') )
    
def createQueue():
    return Queue(EMPTY_NODE, EMPTY_NODE, 0)

def emptyQueue(queue):
    """Is the queue empty?"""
    return queue.front == EMPTY_NODE 

def enqueue(queue, element):
    """Insert an element into the back of the queue"""
    newnode = Node(element, EMPTY_NODE)
    if emptyQueue(queue):
        queue.front = newnode
    else:
        queue.back.next = newnode
    queue.back = newnode
    queue.size += 1
    
def dequeue(queue):
    """Remove the front element from the queue (returns None)"""
    if emptyQueue(queue):
        raise IndexError("dequeue on empty queue") 
    queue.front = queue.front.next
    if emptyQueue(queue):
        queue.back = EMPTY_NODE
    queue.size -= 1
    
def front(queue):
    """Access and return the first element in the queue without removing it"""
    if emptyQueue(queue):
        raise IndexError("front on empty queue") 
    return queue.front.data
    
def back(queue):
    """Access and return the last element in the queue without removing it"""
    if emptyQueue(queue):
        raise IndexError("back on empty queue") 
    return queue.back.data
    
