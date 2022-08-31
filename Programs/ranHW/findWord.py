"""
Program by, Amanda Ramos, 9/18/2015,
"""

def countWords(TFN):
    #TFN= Text File Name
    file=open(TFN)
    wordCt=0
    for line in file:
        if line != '\n':
            wordCt+=1
            for word in line.strip():
                if(word==' ' or word=='\t'):
                    wordCt+=1
    return wordCt

def main():
    print("Number of word in file: ", countWords(input("Enter File Name: ")))

main()

