"""
Lab Session 1
Python & Greedy Algorithm
-Who created Python?
-Explain the game of the name
-What is the current version of Python?
-Explain the term "pythonic"
-Explain the difference between the following:
    -list 
    -tuple
    -set
    -dictionary
    -array
"""
import numpy as np
def kp():
    c = 10
    value = 0
    w = [5,4,3,2]
    v = [3,3,1,3]
    print("C = "+str(c))
    print("W = "+str(w))
    print("V = "+str(v))
    z = tuple(zip(w,v))
    z = sorted(z, reverse=True)
    for i in range(len(z)):
        if(z[i][0] <= c):
            print("weight: "+str(z[i][0])+" value: "+str(z[i][1]))
            #print("weight: "+str(z[i][0]))
            value = value + z[i][1]
            c = c - z[i][0]
    print("Total value = "+str(value))
    
def cmp():
    t = 6
    count = 0
    d = [4, 3, 1]
    print("T = "+str(t))
    print("d = "+str(d))
    for i in range(len(d)):
        while d[i]<=t:
            print("Value = "+str(d[i]))
            t = t-d[i]
            count = count + 1
    print("Number of coins = "+str(count))

def main():
    #print("KP 0/1 with Greedy algorithm:")
#   kp()
    #print("\n")
    #print("CMP with Greedy algorithm:")
    cmp()


main()