import numpy as np

def kp():
    c = 10
    w = [5,4,3,2]
    v = [3,3,1,3]
    n = len(v)
    m = np.zeros([n,c+1])
    for i in range(n):
        for j in range(c+1):
            if w[i] > j:
                m[i][j] = m[i-1][j]
            else:
                m[i][j] = max(m[i-1][j], m[i-1][j-w[i]]+v[i])
    print(str(m))
    i = n-1
    j = c
    while i >= 0:
        if (i == 0) & (j <= w[i]):
            print("Weight = "+str(w[i])+" Value = "+str(v[i]))
            break
        while j >= 0:
            if m[i][j] != m[i-1][j]:
                print("Weight = "+str(w[i])+" Value = "+str(v[i]))
                j = j-w[i]
                i = i-1
                break
            else:
                i = i-1
                break

def cmp():
    t = 6
    d = [4,3,1]
    d.sort()
    n = len(d)
    m = np.zeros([n, t+1])
    for j in range(t+1):
        m[0][j] = j
    for i in range(1,n):
        for j in range(t+1):
            if d[i] > j:
                m[i][j] = m[i-1][j]
            else:
                m[i][j] = min(m[i-1][j], m[i][j-d[i]]+1)            
    print(str(m))
    i = n-1
    j = t
    while i >= 0:
        while j >= 0:
            if (m[i][j] != m[i-1][j]) & (d[i] <= t):
                j = j-d[i]
                print("Value = "+str(d[i]))
            elif (i == 0) & (d[i] <= j):
                j = j-d[i]
                print("Value = "+str(d[i]))
            else:
                i = i-1
                break

def ab(a,b):
    if(a == b):
        return 0
    else:
        return 1

def levenshtein():
    cadena1 = "Honda"
    cadena2 = "Hyundai"
    len1 = len(cadena1)
    len2 = len(cadena2)
    m = np.zeros([len1+1, len2+1])
    for i in range(len1+1):
        m[i][0] = i
    for j in range(len2+1):
        m[0][j] = j
    for i in range(1,len1+1):
        for j in range(1,len2+1):
            if (min(i,j) == 0):
                m[i][j] = max(i,j)
            else:
                par1 = m[i-1][j]+1
                par2 = m[i][j-1]+1
                par3 = m[i-1][j-1]+ab(cadena1[i-1], cadena2[j-1])
                m[i][j] = min(par1, par2, par3)
    print(str(m))
    print("Optimum value = "+str(m[len1][len2]))

#kp()
#print("\n")
cmp()
#print("\n")
#levenshtein()
