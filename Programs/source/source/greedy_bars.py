"""
CSCI-141: Lab F: Greedo The Thief
Author: Sean Strout (sps@cs.rit.edu)
Author: ben k steele (bks@cs.rit.edu)

In this simulation, Greedo is able to fill his satchel, up to its capacity,
with individual metal bars that are sorted by value per bar.

This program, as a collection of modules, requires the rit_lib
module to be installed in the same location as the other
source files.  The rit_lib module is located at:

http://www.cs.rit.edu/~csci141/lib/rit_lib_py.txt

Language: Python 3
"""

__author__ = 'sps'

import satchel      # createSatchel, fillSatchel, printSatchel
import metal        # readMetals, printMetals, sortMetalsByValuePerBar

def main():
    """
    The main program.
    :return None
    :rtype: NoneType
    """

    # prompt for the satchels weight capacity, e.g. 55
    satchelCapacity = int(input("Enter satchel's maximum weight (int): "))
    mySatchel = satchel.createSatchel("Greedo's Satchel", satchelCapacity)

    # prompt for the filename, e.g. precious.txt
    fileName = input('Enter metals file name (in data/): ')

    # read and display the unsorted metals from the file
    print('\nThe metals read from', fileName, ':')
    myMetals = metal.readMetals(fileName)
    metal.printMetals(myMetals)

    # sort the metals by value per bar (ignoring weight)
    print('\nThe metals sorted by value per bar:')
    metal.sortMetalsByValuePerBar(myMetals)
    metal.printMetals(myMetals)

    # fill the satchel greedily by bar value and display result
    print('\nFilling the satchel greedily by bar value:')
    satchel.fillSatchel(mySatchel, myMetals)
    satchel.printSatchel(mySatchel)

if __name__ == '__main__':
    main()
