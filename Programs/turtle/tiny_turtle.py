"""
Program by, Amanda Ramos, 9/29/2015,
"""
import turtle

def ttEvaluate(word):
    start=0
    for i in range(len(word)):
        if(word[i]>="0" and word[i]<="9"):
            turtle.forward(10*int(word[i]))
        elif(word[i]=="<"):
            turtle.left(15)
        elif(word[i]==">"):
            turtle.right(15)
        elif(word[i]=="L"):
            turtle.left(90)
        elif(word[i]=="R"):
            turtle.right(90)
        elif(word[i]==" "):
            pass
        else:
            input("ERROR: "+word[i]+" cannot be computed. "+word[i]+" is an undefined variable.")

#def ttInterpret():

def ttExpand(word):
    str=""
    for i in range(len(word)):
        if(word[i]=="/"):
            str+=ttExpand(word[:i].strip("/"))
        if(word[i]=="="):
            str+="RR"+reverse(mirror(str))+"RR"+mirror(str)
        if(word[i]=="!"):
            str+="RR"+reverse(mirror(str))+"RR"
        if(word[i]=="@"):

        elif not word[i]==" ":
            str+=word[i]
    return str

def mirror(word):
    str=""
    for i in word:
        if(i==">"):
            str+="<"
        elif(i=="<"):
            str+=">"
        elif(i=="L"):
            str+="R"
        elif(i=="R"):
            str+="L"
        else:
            str+=i
    return str

def reverse(word):
    str=""
    for i in range(1,len(word)+1):
        str+=word[-i]
    return str

def main():
    ttEvaluate(ttExpand("5L5!"))

main()
input('Hit enter to close...')