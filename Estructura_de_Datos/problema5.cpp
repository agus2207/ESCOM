/*
AUTOR: Agustin Galindo Reyes       Agosto 2016

DESCRIPCION: Programa solucion al problema 5: "Realizar un programa que muestre la serie de Fibonacci hasta el termino n, el cual es   
recibido al inicio y debe ser mayor a 2. Al mostrar la serie se debera mostrar termino a termino a la salida con un retraso de 0.5 
segundos". El programa se base en el pseudocodigo que nos otorgo el profesor.
 
OBSERVACIONES:
El programa recibe un numero entero mayor a 2, el programa no funcionara de manera correcta si se introducen caracteres, cadenas o 
numeros flotantes.

COMPILACION: gcc programa.cpp
EJECUCIOM: Programa.exe (En Windows)
*/

//LIBRERIAS
#include<stdio.h>  //Incluye las funciones estandar de entrada y salida
#include<stdlib.h> //Incluye las funciones de busqueda y ordenamiento de datos
#include<windows.h>  //Fichero de cabecera que contiene declaraciones de todas las funciones en el API de Windows

//PROGRAMA PRINCIPAL
int main (void)
{
    int n;  //Almacena el termino pedida 
	int primero=0, segundo=1;  //Almacena los 2 primeros valores 0 y 1
	int elemento;  //Almacena el valor de la variable siguiente
	int siguiente;  //Almacena el siguiente numero en la serie
	//Mientras la variable sea menor a 2
	while(n<2)
	{
		printf("Introduzca su termino\n");  //Imprime en la pantalla la leyenda "Introduzca su termino"
		scanf("%d",&n);  //Lee lel termino asignado
		printf("Fibonacci\n\n");  //Imprime en la pantalla la leyenda "Fibonacci"
	}
	printf("%d\n", primero);  //Imprime en la pantalla el primer termino
	Sleep(500);  //Espera 0.5 segundos para arrojar el siguiente resultado
	printf("%d\n",segundo); //Imprime en la pantalla el segundo termino
	Sleep(500);  //Espera 0.5 segundos para arrojar el siguiente resultado
	//Realizaras las siguientes instrucciones
	do
	{
		siguiente=primero+segundo;  //El siguiente termino sera igual a la suma del primer y segundo termino
		printf("%d\n", siguiente); //Se muestran las salidas del algoritmo
		Sleep(500);  //Espera 0.5 segundos para arrojar el siguiente resultado
		primero=segundo;  //El primer elemento sera igual al segundo
		segundo=siguiente;  //El segundo elemento sera igual al siguiente numero de la serie
		elemento=elemento+1;  // La variable elemento se incrementa en 1
	}
	//Mientras el elemento se menor al termino
	while(elemento<n);
	system("pause");  //Nos permite ver el resultado en la pantalla hasta que demos enter
	return 0;	
}
