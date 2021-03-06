from tkinter import *
import math
import random
import functools

#Tamanio de Poblacion
t_Poblacion = 8	
#Cambio a entregar
cambio = 10

#Conteo de Generaciones
generacion = 1

def crearIndividuo():
	individuo = [random.randint(0,veces[i]) for i in range(l_Cromosoma)]
	return individuo

def crearPoblacion():
	global poblacion, valoresFitness
	for i in range(0,t_Poblacion):
		poblacion.append(crearIndividuo())
		valoresFitness.append(0)

#Regresa el cambio que se da con las monedas
def decodificarCromosoma(individuo):
	global W, V
	Vt = 0
	for i in range (l_Cromosoma):
		Vt = Vt + individuo[i]*V[i]
	# print("Cambio dado: "+str(Vt))
	return Vt

#Devuelve cambio dado con las monedas si se pasa lo vuelve negativo si le falta aniade monedas de 1
def fitness (Vt, cromosoma):
	global gamma
	V_total = Vt
	cantidadMonedas = 0
	if (V_total > cambio):
		cantidadMonedas = V_total*gamma
		return cantidadMonedas
	elif V_total == cambio:
		for moneda in cromosoma:
			cantidadMonedas = cantidadMonedas + moneda
		return cantidadMonedas
	else:
		falta = cambio - V_total
		cromosoma [0] = cromosoma[0] + falta
		for moneda in cromosoma:
			cantidadMonedas = cantidadMonedas + moneda
		return cantidadMonedas

def evaluarCromosomas():
	global valoresFitness, poblacion
	for i in range (len(poblacion)):
		V = decodificarCromosoma(poblacion[i])
		valoresFitness[i]=fitness(V,poblacion[i])

def compararCromosomas(cromosoma1,cromosoma2):
	# print("Cromosoma 1: "+str(cromosoma1))
	# print("Cromosoma 2: "+str(cromosoma2))
	V1=decodificarCromosoma(cromosoma1)
	V2=decodificarCromosoma(cromosoma2)
	fitness_Value1=fitness(V1, cromosoma1)
	fitness_Value2=fitness(V2, cromosoma2)
	# print("Cromosoma | Monedas: "+str(cromosoma1)+", "+str(fitness_Value1))
	# print("Cromosoma | Monedas: "+str(cromosoma2)+", "+str(fitness_Value2))
	if fitness_Value1 > fitness_Value2:
		return 1
	elif fitness_Value1 == fitness_Value2:
		return 0
	else: #fitness_Value1 < fitness_Value2
		return -1

def crearRueda ():
	global valoresFitness
	
	maxv=max(valoresFitness)
	acc=0
	for p in range(t_Poblacion):
		acc+=maxv-valoresFitness[p]
	fraction=[]
	for p in range(t_Poblacion):
		fraction.append(float(maxv-valoresFitness[p])/acc)
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
	if decodificarCromosoma(poblacion[0]) == cambio:
		print("\nMejor solucion hasta el momento:")
		print("f("+str(decodificarCromosoma(poblacion[0]))+")= "+str(fitness(decodificarCromosoma(poblacion[0]), poblacion[0]))+" monedas")
	else:
		print("Aun no hay solucion")
	#Aplicamos Elitismo, los mejores dos individuos iran directamente a la siguiente generacion
	# print("Mejor individuo 1: "+str(poblacion[0]))
	# print("Mejor individuo 2: "+str(poblacion[1]))
	nuevaPoblacion[0]=poblacion[0]
	nuevaPoblacion[1]=poblacion[1]
	# nuevaPoblacion[2]=poblacion[2]
	# nuevaPoblacion[3]=poblacion[3]
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
			# o1[int(round(random.random()*(l_Cromosoma-1)))]= random.randint(0,2)
			o1[random.randint(0,l_Cromosoma-1)]= random.randint(0,veces[0])
		if random.random() < mutacion:
			# o2[int(round(random.random()*(l_Cromosoma-1)))]= random.randint(0,2)
			o2[random.randint(0,l_Cromosoma-1)]= random.randint(0,veces[0])
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

gamma = 10

Lrueda=t_Poblacion*10

#Denominaciones de nuestras monedas
V = []
V.append(1)
V.append(2)
V.append(5)
V.append(10)


# for i in range(0,l_Cromosoma):
	# V.append(2**i)
# for i in range(1,l_Cromosoma):
	# numA = random.choice(monedas)
	# monedas.remove(numA)
	# V.append(numA)

veces = []
for moneda in V:
	v = cambio // moneda
	veces.append(v)	
print("Denominaciones:"+str(V))
print("Cambio a dar: \t"+str(cambio))
# print("Cantida de veces: "+str(veces))

#Longitud de Cromosoma (Cantidad de Objetos)
l_Cromosoma = len(V)

#Punto de Cruce
puntoCruce=l_Cromosoma//2

poblacion = []
valoresFitness = []
crearPoblacion()

print("------------------------\n\nGeneracion: "+str(generacion))
print("Poblacion: \n"+str(poblacion))

poblacion.sort(key=functools.cmp_to_key(compararCromosomas))
# print "Poblacion: \n", poblacion
evaluarCromosomas()

print("\n------------------------\n")

nuevaPoblacion = poblacion[:]

mainloop()