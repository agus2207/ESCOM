#File example_ga.py
#Example of GA
#Dr. Jorge Luis Rosas Trigueros
#Last change: 18feb20


from tkinter import *
import math
import random
import functools
#Chromosomes are 4 bits long
L_chromosome=32
N_chains=2**L_chromosome
#Lower and upper limits of search space
a=-5
b=5
crossover_point=L_chromosome//2


def random_chromosome():
    chromosome=[]
    for i in range(0,L_chromosome):
        if random.random()<0.1:
            chromosome.append(0)
        else:
            chromosome.append(1)

    return chromosome

#Number of chromosomes
N_chromosomes=100
#probability of mutation
prob_m=1

F0=[]
fitness_values=[]

for i in range(0,N_chromosomes):
    F0.append(random_chromosome())
    fitness_values.append(0)

#binary codification
def decode_chromosome(chromosome):
    global L_chromosome,N_chains,a,b
    value=0
    for p in range(L_chromosome):
        value+=(2**p)*chromosome[-1-p]

    return a+(b-a)*float(value)/(N_chains-1) #in Python3, conversion to float is not needed



def f(x,y):
    term1 = - 20 * math.exp(-0.2 * math.sqrt(0.5 * (abs(x**2)+abs(y**2))))
    term2 = - math.exp(0.5 * (math.cos(2 * math.pi  * x)+math.cos(2 * math.pi  * y)))
    return term1 + term2 + 20 + math.exp(1)



def evaluate_chromosomes():
    global F0

    for p in range(N_chromosomes//2):
        v=decode_chromosome(F0[p])
        w=decode_chromosome(F0[p+(N_chromosomes//2)])
        fitness_values[p]=f(v,w)
        

def compare_chromosomes(chromosome1,chromosome2):
    vc1=decode_chromosome(chromosome1)
    vc2=decode_chromosome(chromosome2)
    fvc1=f(vc1,vc1)
    fvc2=f(vc2,vc2)
    if fvc1 > fvc2:
        return 1
    elif fvc1 == fvc2:
        return 0
    else: #fvg1<fvg2
        return -1


suma=float(N_chromosomes*(N_chromosomes+1))/2.

Lwheel=N_chromosomes*10

def create_wheel():
    global F0,fitness_values

    maxv=max(fitness_values)
    acc=0
    for p in range(N_chromosomes):
        acc+=maxv-fitness_values[p]
    fraction=[]
    for p in range(N_chromosomes):
        fraction.append( float(maxv-fitness_values[p])/acc)
        if fraction[-1]<=1.0/Lwheel:
            fraction[-1]=1.0/Lwheel
    fraction[0]-=(sum(fraction)-1.0)/2
    fraction[1]-=(sum(fraction)-1.0)/2

    wheel=[]

    pc=0

    for f in fraction:
        Np=int(f*Lwheel)
        for i in range(Np):
            wheel.append(pc)
        pc+=1

    return wheel
        
F1=F0[:]

def nextgeneration():
    w.delete(ALL)
    B = F0[:len(F0)//2]
    C = F0[len(F0)//2:]
    B.sort(key=functools.cmp_to_key(compare_chromosomes))
    C.sort(key=functools.cmp_to_key(compare_chromosomes))
    print( "Best solution so far:" )
    print( "f("+str(decode_chromosome(B[0]))+","+str(decode_chromosome(C[0]))+")= "+
           str(f(decode_chromosome(B[0]), decode_chromosome(C[0]))) )
                                                                    
    #elitism, the two best chromosomes go directly to the next generation
    F1[0]=F0[0]
    F1[1]=F0[1]
    for i in range(0,(N_chromosomes-2)//2):
        roulette=create_wheel()
        #Two parents are selected
        p1=random.choice(roulette)
        p2=random.choice(roulette)
        #Two descendants are generated
        o1=F0[p1][0:crossover_point]
        o1.extend(F0[p2][crossover_point:L_chromosome])
        o2=F0[p2][0:crossover_point]
        o2.extend(F0[p1][crossover_point:L_chromosome])
        #Each descendant is mutated with probability prob_m
        if random.random() < prob_m:
            o1[int(round(random.random()*(L_chromosome-1)))]^=1
        if random.random() < prob_m:
            o2[int(round(random.random()*(L_chromosome-1)))]^=1
        #The descendants are added to F1
        F1[2+2*i]=o1
        F1[3+2*i]=o2

    #The new generation replaces the old one
    F0[:]=F1[:]



#visualization
master = Tk()

xmax=400
ymax=400

xo=200
yo=200

s=10
sy=5

w = Canvas(master, width=xmax, height=ymax)
w.pack()

            
button1 = Button(master, text="Next Generation", command=nextgeneration)
button1.pack()

N=100

F0.sort(key=functools.cmp_to_key(compare_chromosomes))
evaluate_chromosomes()



mainloop()