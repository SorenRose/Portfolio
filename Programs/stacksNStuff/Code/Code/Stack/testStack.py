"""
Test stack data structure.
file: testStack.py
author: Sean Strout
"""

from myStack import *

def main():
    # begin with an empty stack
    stackCh = EMPTY_NODE      	
    print("Creating empty stack...")
    print("Stack empty?", True == emptyStack(stackCh))
    print("Stack size is 0?",  0 == size(stackCh))   
    
    # add first element
    print("push A...")
    stackCh = push(stackCh, 'A')
    print("Stack not empty?", False == emptyStack(stackCh))
    print("Stack size is 1?", 1 == size(stackCh))   
    print("top is A?", 'A' == top(stackCh))
    
    # add second element
    print("push B...")
    stackCh = push(stackCh, 'B')
    print("top is B?", 'B' == top(stackCh))
    
    # add third element
    print("push C...")
    stackCh = push(stackCh, 'C')
    print("top is C?", 'C' == top(stackCh))
    print("Stack size is 3?", 3 == size(stackCh))   
      
    # pop top element, C
    print("pop...")
    stackCh = pop(stackCh)
    print("Stack not empty?", False == emptyStack(stackCh))
    print("Stack size is 3?", 2 == size(stackCh))   
    print("top is B?", 'B' == top(stackCh))
    
    # add fourth element
    print("push D...")
    stackCh = push(stackCh, 'D')
    print("top is D?", 'D' == top(stackCh))
    
    # Empty the stack
    print("Emptying the stack...")
    while not emptyStack(stackCh):
        print("Top of stack:", top(stackCh))
        print("pop...")
        stackCh = pop(stackCh)
    
main()
