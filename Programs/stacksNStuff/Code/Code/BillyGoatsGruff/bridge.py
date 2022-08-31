from myQueue import *
from myStack import *
from random import *

def getBridgeMaxWeight():
    return randint(700, 1100)
    
def getBridgeMaxGoats():
    return randint(3, 5)
    
def crossTheBridge(cavern, goats):
    """Goats leave the cavern and enter the bridge"""
    bridge = createQueue()
    
    print("STAGE 2: SKILL CHALLENGE...")
    print("The goats make it to a perilous gorge...")
    print("There is an old and rotting wooden bridge that spans the gorge...")
    print("The berries are on the other side of the bridge!")
    print("The goats decide to cross it together...")
    
    MAX_WEIGHT = getBridgeMaxWeight()
    print("The maximum weight supported by the bridge is", MAX_WEIGHT);
    
    MAX_GOATS = getBridgeMaxGoats()
    print("The bridge can hold a maximum of", MAX_GOATS, "goats")
    
    # loop by adding goats to the bridge until either it breaks or 
    # they all make it on
    totalWeight = 0
    brokeBridge = False
    while not brokeBridge and not emptyStack(cavern):
        # if the bridge holds the max goats, remove the front goat from it
        if bridge.size == MAX_GOATS:
            goat = front(bridge)
            print("The bridge is full.", goat.name, "finishes crossing it...")
            enqueue(goats, front(bridge))
            dequeue(bridge)
            totalWeight -= goat.weight
            
        # process the next goat in the cavern
        goat = top(cavern)
        cavern = pop(cavern)
        if not brokeBridge:
            print(goat.name, "of weight", goat.weight, 
                "steps carefully onto the bridge...")
            enqueue(bridge, goat)
            totalWeight += goat.weight
            print("Total weight on bridge:", totalWeight)
            print("Number of goats on the bridge:", bridge.size)
            if totalWeight > MAX_WEIGHT:
                print("OH NO! The bridge collapses under the massive weight!")
                brokeBridge = True

    
    # if the bridge breaks, separate those who fall from those 
    # who are stranded
    if brokeBridge:
        while not emptyQueue(bridge):
            # the goats on the bridge are in trouble
            print(front(bridge).name, "falls into the gorge!")
            dequeue(bridge)
        while not emptyStack(cavern):
            goat = top(cavern)
            cavern = pop(cavern)
            print(goat.name, "is left stranded in the fields!")
    else:
        while not emptyQueue(bridge):
            goat = front(bridge)
            print(goat.name, "finishes crossing the bridge...")
            enqueue(goats, front(bridge))
            dequeue(bridge)
        
    print(str(goats.size), "goat/s survived the bridge crossing...")  
