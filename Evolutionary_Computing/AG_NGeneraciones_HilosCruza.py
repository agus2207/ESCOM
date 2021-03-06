import random as rand
import threading
from random import random

NumeroPoblaciones = 16

#Este porgrama Crean N poblaciones cada una con 20 genraciones utilizando un solo proceso pero el proceso se ayuda con 2 hilos para realizar el la de cruza

"""
Objetivo del programa:
	Resolver el problema de la mochila con un algoritmo genetico

Estructura del programa:
	Alelos:			peso y valor
					Se podria decir que un alelo es la parte atomica de una solucion, es indivisible y representa un valor en la estructura de nuestra solucion

	Genes:			objeto = (peso, valor)
					Los genes son elementos que conforman un cromosoma o individuo, en este caso el individuo es la mochila y el gen un objeto de la mochila

	Cromosomas:	[objeto_1, objeto_2, ... , objeto_n]
					Los cromosomas son soluciones potenciales al problema que tratamos de resolver, en este caso un cromosoma (o individuo) representa una mochila
Funciones utilizadas:
	len(x)					Devuelve la cantidad de objetos en x
	radom.randint(0, n)		Devuelve un entero entre 0 y n
	lista.pop(i)			Elimina el i-esimo objeto de la lista, en caso de no proporcionar un index elimina el ultimo objeto en la lista
	random.choice(x)		Devuelve un elemento aleatorio de x
	lista.append(a)			Ingresa en la lista el parametro a en el tope
	max(lista)				Devuele el valor mayor en lista
	lista.index(x)			Devuelve la posicion de la lista del elemento x
	float(x)				cast a float de x, puede ser una cadena o entero

Material util:
	Documentacion oficial de python:	https://docs.python.org/2/
		-random:						https://docs.python.org/2/library/random.html - aqui podran ver como modificar una semilla
		-listas y tuplas:				https://docs.python.org/2/tutorial/datastructures.html
		-hilos:							https://docs.python.org/2/library/threading.html
"""
##	Variables globales
W = 50											#peso que soporta la mochila
genes = []										#cada gen representa un objeto de la mochila
tPoblacion = 50									#cantidad de individuos por poblacion
num_Generaciones = 20							#veces en las que se iterara el algoritmo
c_objetos = 125	#input("Ingrese la cantidad de objetos a generar: ")	#cantidad de objetos que a generar (recomiendo un valor entre 100 y 150; valor maximo soportado (en pruebas): 870)

probabilidad_Mutacion = []					#probabilidad de mutacion definismos una para cada poblacion a crear
for i in range(NumeroPoblaciones):
	probabilidad_Mutacion.append(random()) #Creasmo un rand entre 0 y 1
	print("ProbabilidadMutacion para el hilo [", i , "]: ", probabilidad_Mutacion[i])

##	Funciones
def inicializar_individuo():					#funcion para generar individuos
	global genes, W
	peso = 0									#peso que lleva el individuo generado
	mochila = [ ]								#individuo, cada individuo es una solucion posible
	while (peso < W):
		pos = rand.randint(0, len(genes)-1)		#posicion (index) aleatoria de la lista de objetos
		objeto = genes[pos]						#objeto que hace referencia el index
		if (peso > W):
			mochila.pop()						#sacar el ultimo objeto de la mochila
		elif ((objeto[0] + peso) > W ):			#condicion para verificar el peso de la mochila con el peso del objeto

			pass								#objeto[0] es el peso, ya que: obj = (peso, valor)
		else:
			mochila.append(objeto)				#agregar objeto a la mochila
			peso = peso + objeto[0]				#sumar el peso del objeto al peso de la mochila
		#print "Agregado: ", objeto
	return mochila

def generar_Poblacion(n):							#funcion para generar la poblacion inicial
	poblacion = []									#poblacion a generar, es una lista de listas (arreglo bidimensional)
	for i in range(n):
		poblacion.append(inicializar_individuo())	#insertar un individuo (mochila) en la poblacion n-veces
	return poblacion

def cruza(padre1, padre2):						#cruza entre individuos, retorna un nuevo individuo
	#print padre1
	#print padre2
	global W									#peso de la mochila
	offspring = []								#individuo a generar
	peso_individuo = 0							#peso del individuo (nueva mochila)
	aux1 = padre1[:]							#copiar padre1 en un auxiliar, con el objetico de modificarlo. Si hacemos: aux1 = padre1; y modificamos aux1 tambien modificamos a padre1
	aux2 = padre2[:]							#copiar padre2 en un auxiliar

	while (peso_individuo < W):
		#print "peso individuo: ", peso_individuo
		#print "aux1", aux1
		#print "aux2", aux2
		#print "a OFFSPRING", offspring

												#este 'switch' es para evitar usar un auxiliar vacio
		if not aux1 and not aux2:				#not aux1 significa que aux1 no tiene elementos, es equivalente a len(aux1) == 0
			return offspring
		elif not aux1:							#aux1 es vacio?
			a = 0								#tomar elementos de aux2
		elif not aux2:							#aux2 es vacio?
			a = 1								#tomar elementos de aux1
		else:
			a = rand.randint(0, 1)				#en caso de que ningun auxiliar sea vacio elegimos uno aleatoriamente

		if (a):										#seleccion del padre que hereda
			objeto = rand.choice(aux1)				#objeto a heredar
			if (objeto[0] + peso_individuo > W):	#objeto[0] = peso; esto es para ver si la mochila se desborda (hablando de su peso)
				aux1.pop( aux1.index(objeto) )		#eliminar objeto de espacio muestral
			else:
				offspring.append(objeto)			#insertar objeto
				peso_individuo = peso_individuo + objeto[0]	#sumar peso a la mochila
				aux1.pop( aux1.index(objeto) )		#eliminar objeto de espacio muestral (para evitar repeticiones)
		else:									#lo mismo pero con otro padre
			objeto = rand.choice(aux2)				#objeto a heredar
			if (objeto[0] + peso_individuo > W):	#objeto[0] = peso; esto es para ver si la mochila se desborda (hablando de su peso)
				aux2.pop( aux2.index(objeto) )		#eliminar objeto de espacio muestral
			else:
				offspring.append(objeto)			#insertar objeto
				peso_individuo = peso_individuo + objeto[0]	#sumar peso a la mochila
				aux2.pop( aux2.index(objeto) )		#eliminar objeto de espacio muestral (para evitar repeticiones)

	return offspring

def peso(individuo):				#funcion para saber el peso de una mochila
	peso = 0
	for gen in individuo:
		peso = peso + gen[0]
	return float(peso)

def valor(individuo):				#funcion para saber el valor acumulado de una mochila
	v = 0
	for gen in individuo:
		v = v + gen[0]
	return float(v)

def aptitud(individuo):							#calcular peso y valor para determinar la aptitud de la mochila
	#print "INIDIVIDUO:	", individuo
	suma_peso = 0.0								#peso total de la mochila
	suma_valor = 0.0							#valor total de la mochila
	for gen in individuo:
		suma_peso = suma_peso + gen[0]			#gen[0] <- peso
		suma_valor = suma_valor + gen[1]		#gen[1] <- valor
	aptitud = suma_valor/suma_peso				#relacion valor/peso
	return aptitud


def nueva_Poblacion(old_population):			#fucion para generar una nueva poblacion
	global tPoblacion
	siguiente_Generacion = []
	siguiente_Generacion1 = []
	siguiente_Generacion2 = []

	threads = list() #lista para los hilos 
	#print "Hilos generados para cruza: ", 2 ##apartamos dos posisciones para los 2 hilos
	for i in range(2):
		threads.append(i)

	#A cada hilo le mandamos una parte del tamanio de la poblacion
	threads[0] = threading.Thread(target=cruzarDosMochilas, args=(old_population[:tPoblacion//2],siguiente_Generacion1))
	threads[0].start()

	threads[1] = threading.Thread(target=cruzarDosMochilas, args=(old_population[tPoblacion//2:],siguiente_Generacion2))
	threads[1].start()

	threads[0].join()
	threads[1].join()

	siguiente_Generacion = siguiente_Generacion1 + siguiente_Generacion2


	return siguiente_Generacion

def cruzarDosMochilas(old_population,siguiente_Generacion): #como se reciben por referencia no se necesita retornar nada

	for x in range(len(old_population)//2):		#en cada iteracion se generan dos individuos
		#print "DEBUG DEBUG DEBUG DEBUG"
		padre1 = old_population[x]
		padre2 = old_population[(len(old_population)//2) + x]	#no hay que olvidar que estamos iterando en N/2 elementos, mientras que tenemos N elementos
		#print "p1", padre1
		#print "p2", padre2

		offs1 = cruza(padre1, padre2)			#hijo 1
		offs2 =	cruza(padre2, padre1)			#hijo 2
		#print "o1", offs1
		#print "o2", offs2

		siguiente_Generacion.append(offs1)	#insertar hijo 1 en la nueva poblacion
		siguiente_Generacion.append(offs2)		#insertar hijo 2 en la nueva poblacion



#metodo de seleccion:	torneo					#El metodo del torneo elige al individuo con mayor aptitud de una poblacion
												#Este metodo no es recomendable para generar diversidad
												#Aunque es una buena manera de mantener al mejor individuo por varias generaciones hasta que llegue uno mejor
def seleccion_Torneo(poblacion):
	aptitudes = []									#aptitud de cada miembro de la poblacion
	for individuo in poblacion:
		fitness =  aptitud(individuo)				#calcular aptitud de cada individuo
		#print fitness
		aptitudes.append(fitness)					#insertar en una lista las aptitudes de los individuos

	pos = aptitudes.index(max(aptitudes))			#posicion del mejor individuo
	mejor = poblacion[pos]							#mejor individuo
	#print "POS: ", pos
	#print "MEJOR: ", mejor

	return mejor

def mutar(individuo,probabilidad_Mutacion):									#mutacion
	global W, genes				#se usaran los objetos generados para reemplazar un objeto del individuo (mochila) que se envia como parametro
	prob = rand.random()								#probabilidad de mutar aleaotoria entre [0.0, 1.0]
	peso_individuo = peso(individuo)					#peso del individuo
	aux = individuo[:]									#copia del individuo a mutar, este valor sera modificado

	if (prob < probabilidad_Mutacion):					#en caso de ser verdadero el individuo sera mutado
		pos = rand.randint(0, len(individuo)-1)			#posicion del objeto a modificar

		while True:										#ciclo equivalente a:	do {...} while ( )
			piv = rand.randint(0, len(genes)-1)			#seleccionar un objeto de los genes
			objeto = genes[piv]							#el objeto que reemplazara o sera insertado en la mochila. Las dos ultimas lineas son equivalentes a: objeto = rand.choice(genes)
			#print objeto

			if (objeto[0] + peso(individuo) <= W):			#en este escenario el objeto sera insertado en vez de sustituir a otro, ya que puede entrar a la mochila sin romper la regla del peso
				aux.append(objeto)
				break
			elif (individuo[pos] != genes[piv]):			#en caso de no cumplirse: volver a generar el objeto que reemplazara
				aux[pos] = objeto							#sustituir objeto
				break

		return aux

###	MAIN

def main():	
													#dummy main() o main() falso
	global tPoblacion, c_objetos, genes, num_Generaciones
	
	global NumeroPoblaciones 

	print("Numero de Poblaciones a generar: ", NumeroPoblaciones)
	for i in range(NumeroPoblaciones):
		#INICIALIZACION
		while len(genes) != c_objetos:							#en este ciclo se van a generar los objetos que llevaremos en la mochila
			obj = (rand.randint(1, 30), rand.randint(0, 30))	#acotar el dominio. El peso empieza en 1, ya que si es cero existe la posibilidad de que se ingresen infinitos objetos con peso 0
			if obj not in genes:								#determinar si el objeto a generar ya se ha generado y existe en la lista
				genes.append(obj)								#insertar objeto (gen) en la lista de objetos (genes)

		#print "Objetos generados:"
		#for objeto in genes: print objeto						#imprime los objetos generados

		"""
		aqui van los hilos										#los hilos se generan despues de los objetos ya que si generamos objetos en cada hilo estariamos resolviendo un problema diferente en cada hilo
		"""

		poblacion = generar_Poblacion(tPoblacion)				#poblacion inicial
		#print "Poblacion inicial (f0):"
		#i = 0
		#for individuo in poblacion:
		#	i = i + 1
		#	print "Individuo ", i, ":	",  individuo			#imprime los miembros de la poblacion generada

		mejor_solucion = seleccion_Torneo(poblacion)			#seleccion del mejor individuo
		print("Poblacion",i,"Generacion: 0 aptitud de la mejor solucion:	", aptitud(mejor_solucion))

		for x in range(num_Generaciones):
			#CRUZA
			poblacion = nueva_Poblacion(poblacion)				#sustituir la poblacion actual con una nueva
			#MUTACION
			for individuo in poblacion:
				individuo = mutar(individuo,probabilidad_Mutacion[i])					#mutar a cada individuo, en la funcion mutar() se determina si es mutado o no
			#SELECCION
			poblacion[0] = mejor_solucion						#elitisimo, la mejor solucion se mantiene en la siguiente poblacion
			#mejor_solucion = seleccion_Torneo(poblacion)

			#imprimir poblacion
			#for I in range(len(poblacion)-1):
			#	individuo = poblacion[I]
			#	print I, "	", individuo

			mejor_solucion = seleccion_Torneo(poblacion)		#volver a determinar la mejor solucion
			if x == 19:
				print("Poblacion",i,"Generacion: ", x+1, "aptitud de la mejor solucion:	 ", aptitud(mejor_solucion))
			#print "Mutacion"


if __name__ == "__main__":								#main del programa
	main()
