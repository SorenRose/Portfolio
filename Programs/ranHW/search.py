"""
Program by: Amanda Ramos, Oct 2nd, 2015
"""

def searchChar(find,word):
    if word=='':
        return False
    elif find==isHead(word):
        return True
    else:
        return searchChar(find,isTail(word))

def matchString(find,word):
    if find=="":
        return True
    elif word=="":
        return False
    elif isHead(find)==isHead(word):
        return matchString(isTail(find),isTail(word))
    else:
        return False


def searchString(find,word):
        if find=="":
            return True
        elif word=="":
            return False
        elif isHead(find)==isHead(word):
            return searchString(isTail(find),isTail(word))
        elif isHead(find)!=isHead(word):
            return matchString(find,isTail(word))



def matchPat(find,word):
    if find=="":
        return True
    elif word=="":
        return False
    elif isHead(find)==isHead(word):
        return matchPat(isTail(find),isTail(word))
    elif isHead(find)!=isHead(word):
        if isHead(find)=="*":
            return matchPat(isTail(find),word)
        else:
            return matchPat(find,isTail(word))


def searchPat(find,string):
    return matchPat(find,string)

def isHead(word):
    return word[0]

def isTail(word):
    return word[1:]

def main():
    print(searchChar('r','run'))#in searchChar this tests if the method understands that the first char matches the first char or the word
    print(searchChar("n","kitten"))#in searchChar this tests if the method returns true if the letter isn't at the beginning but still in the word in a different place
    print(searchChar("b","run"))#in searchChar this tests if the method returns false is the char isn't in the word
    print(matchString("fi","finally"))#in matchString this tests if the first two chars match the first two of the word
    print(matchString("fi","the"))#in matchString this checks that the first char matches the first char of the word but since it doesn't it returns false
    print(searchString("kit","kitten"))#in searchString this checks if "kit" is in "kitten", since it is, it returns true
    print(searchString("an","ran"))#in searchString this checks if "an" is in the word "ran" regardless of placement
    print(searchString("vy","very"))#in searchString this checks if "vy" is consecutively in the word "very"
    print(matchPat("q*k*y","quickly"))#in matchPat this checks if the pattern of q, k, and y are all in the word "quickly" in that order
    print(matchPat("f*m*o","from"))#in matchPat, this checks if the pattern of f, m, and o are in "from" in that specific order regardless of placement
    print(searchPat("t*s*p","the stormtrooper"))#in searchPat this tests if the pattern t, s, and p are in the set of words in the string match that pattern
    print(searchPat("o*s*d","on the deathstar"))#in searchPat this tests if the pattern o, s, and d are in the set or words in the order stated previously

    input("Press Enter to exit this program")

main()