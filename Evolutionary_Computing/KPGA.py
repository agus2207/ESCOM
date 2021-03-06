from tkinter import *
import math
import random
import functools

#Longitud de Cromosoma (Cantidad de Objetos)
l_Cromosoma = 5
#Tamanio de Poblacion
t_Poblacion = 8	
#Peso Maximo
Wmax = 10

#Conteo de Generaciones
generacion = 1

#Punto de Cruce
puntoCruce=l_Cromosoma//2

def crearIndividuo():
	individuo = [random.randint(0,1) for i in range(l_Cromosoma)]
	return individuo

def crearPoblacion():
	global poblacion, valoresFitness
	for i in range(0,t_Poblacion):
		poblacion.append(crearIndividuo())
		valoresFitness.append(0)

#Regresa un tupla con el peso total y el beneficio total de cada individuo
def decodificarCromosoma(individuo):
    global W, V
    Pt_Vt = []
    Pt = 0
    Vt = 0
    for i in range (l_Cromosoma):
        Pt = Pt + individuo[i]*W[i]
        Vt = Vt + individuo[i]*V[i]
        #print("Pt = "+str(Pt))
        #print("Vt = "+str(Vt))
    Pt_Vt.append(Vt)
    Pt_Vt.append(Pt)
    #print("BeneficioTotal | PesoTotal: "+str(Pt_Vt))
    return Pt_Vt

#Devuelve el beneficio del individuo, si el peso pasa el peso maximo se penaliza
def fitness (Pt_Vt):
    global gamma
    V_total = Pt_Vt[0]
    W_total = Pt_Vt[1]
    if (W_total > Wmax):
        return V_total - gamma*(W_total - Wmax)
    else:
        return V_total

def evaluarCromosomas():
    global valoresFitness, poblacion
    for i in range (len(poblacion)):
        W_V = decodificarCromosoma(poblacion[i])
        valoresFitness[i]=fitness(W_V)

def compararCromosomas(cromosoma1,cromosoma2):
    W_V1=decodificarCromosoma(cromosoma1)
    W_V2=decodificarCromosoma(cromosoma2)
    fitness_Value1=fitness(W_V1)
    fitness_Value2=fitness(W_V2)
    if fitness_Value1 < fitness_Value2:
        return 1
    elif fitness_Value1 == fitness_Value2:
        return 0
    else: #fitness_Value1 > fitness_Value2
        return -1

def crearRueda ():
	global valoresFitness
	
	maxv=max(valoresFitness)
	acc=0
	for p in range(t_Poblacion):
		acc+=maxv-valoresFitness[p]
	fraction=[]
	for p in range(t_Poblacion):
		fraction.append( float(maxv-valoresFitness[p])/acc)
		if fraction[-1]<=1.0/Lrueda:
			fraction[-1]=1.0/Lrueda
	fraction[0]-=(sum(fraction)-1.0)/2
	fraction[1]-=(sum(fraction)-1.0)/2

	wheel=[]
	pc=0
	
	for f in fraction:
		Np=int(f*Lrueda)
		for i in range(Np):
			wheel.append(pc)
		pc+=1
    
	return wheel

def nuevaGeneracion():
    global generacion
    generacion += 1
    print("Generacion: "+str(generacion))
    poblacion.sort(key=functools.cmp_to_key(compararCromosomas))
    print("Poblacion Antigua Ordenada: \n"+str(poblacion))
    aux = decodificarCromosoma(poblacion[0])
    #print("Auxiliar = "+str(aux[1]))
    if(aux[1] <= Wmax):
        print("\nMejor solucion hasta el momento:")
        print("f("+str(decodificarCromosoma(poblacion[0]))+")= "+str(fitness(decodificarCromosoma(poblacion[0]))))
    else:
        print("No hay solucion")
    #Aplicamos Elitismo, los mejores dos individuos iran directamente a la siguiente generacion
    # print("Mejor individuo 1: "+str(poblacion[0]))
    # print("Mejor individuo 2: "+str(poblacion[1]))
    nuevaPoblacion[0]=poblacion[0]
    nuevaPoblacion[1]=poblacion[1]
    for i in range(0,(t_Poblacion-2)//2):
        ruleta=crearRueda()
        #Se seleccionan dos progenitores al azar
        p1=random.choice(ruleta)
        # print("Progenitor 1: "+str(p1))
        p2=random.choice(ruleta)
        # print("Progenitor 2: "+str(p2))
        #Se crean dos Descendientes haciendo uso del punto de Cruce
        o1=poblacion[p1][0:puntoCruce]
        o1.extend(poblacion[p2][puntoCruce:l_Cromosoma])
        # print("Descendiente 1 antes de mutar: "+str(o1))
        o2=poblacion[p2][0:puntoCruce]
        o2.extend(poblacion[p1][puntoCruce:l_Cromosoma])
        # print("Descendiente 2 antes de mutar: "+str(o2))
        #Cada descendiente es mutado con una probabilibad "mutacion"
        if random.random() < mutacion:
            o1[int(round(random.random()*(l_Cromosoma-1)))]^=1
        if random.random() < mutacion:
            o2[int(round(random.random()*(l_Cromosoma-1)))]^=1
        #Los descendientes son agregados a la nueva poblacion
        # print("Descendiente 1 despues de mutar: "+str(o1))
        # print("Descendiente 2 despues de mutar: "+str(o2))
        nuevaPoblacion[2+2*i]=o1
        nuevaPoblacion[3+2*i]=o2
    #La nueva generacion remplaza a la vieja
    poblacion[:]=nuevaPoblacion[:]
    print("Poblacion: \n"+str(poblacion))
    print("\n------------------------\n")

def play():
    for i in range(10000):
        nuevaGeneracion()

# Boton para siguiente generacion:
master = Tk() 
button1 = Button(master, text="Next 10000 Generations", command=play)
button1.pack()

button2 = Button(master, text="Next Generation", command=nuevaGeneracion)
button2.pack()


#Probabilidad de Mutacion
mutacion=0.5	

gamma = 3

Lrueda=t_Poblacion*10

W = [random.randint(1,Wmax) for i in range(l_Cromosoma)]
print("Pesos: \t\t" +str(W))
V = [random.randint(1,5) for i in range(l_Cromosoma)]
print("Beneficio: \t" +str(V))
print("Peso Maximo: \t" +str(Wmax))

poblacion = []
valoresFitness = []
crearPoblacion()

print("------------------------\n\nGeneracion: " +str(generacion))
print("Poblacion: \n"+str(poblacion))

poblacion.sort(key=functools.cmp_to_key(compararCromosomas))
evaluarCromosomas()

print("\n------------------------\n")

nuevaPoblacion = poblacion[:]

mainloop()