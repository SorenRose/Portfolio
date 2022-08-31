'''
Program by Amanda Ramos Oct. 3rd, 2015
1.the insertion sort would take less time because if we had a set of numbers 1342, selection sort would run
1342,1243,1234,while insertion sort would run 1342,1234
2.in some cases, insertion sort would perform faster than selection sort
'''

def selectionSort(list):
    for i in range(len(list)):
        swap(i,lowestNum(i,list),list)
    return list

def lowestNum(place,list):
    num=place
    for i in range(place,len(list)):
        if list[num]>list[i]:
            num=i
    return num

def swap(place,low,list):
    hold=list[place]
    list[place]=list[low]
    list[low]=hold

def main():
    list1=[3,2,8,6,1,9,78,93,11,24,590,0,99,99,99999999,167479275,13234]
    list2=[]
    file=open(input("Enter your file name here: "))
    for i in file:
        list2.append(i)
    #print(list1)
    print(list2)
    #print(selectionSort(list1))
    print(selectionSort(list2))
    input("Press enter to exit this hell")

main()