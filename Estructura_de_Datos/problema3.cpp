/*
AUTOR: Agustin Galindo Reyes       Agosto 2016

DESCRIPCION: Programa solucion al problema 3: "Dados 3 numeros enteros de entrada a, b y c, mostrarlos a la salida ordenados 
de manera descendente". El programa se base en el diagrama de flujo que nos otorgo el profesor.
 
OBSERVACIONES:
El programa recibe los 3 numeros enteros, el programa no funcionara de manera correcta si se introducen caracteres, cadenas o 
numeros flotantes.

COMPILACION: gcc programa.cpp
EJECUCIOM: Programa.exe (En Windows)
*/

//LIBRERIAS
#include<stdio.h>  //Incluye las funciones estandar de entrada y salida
#include<stdlib.h>  //Incluye las funciones de busqueda y ordenamiento de datos

//PROGRAMA PRINCIPAL
int main(void)
{
	int a,b,c; //Almacena los 3 numeros enteros 
	printf("Introduzca 3 numeros enteros\n");  //Imprime en la pantalla la leyenda "Introduzca 3 numeros enteros"
	scanf("%d",&a);  //Lee el primer numero
	scanf("%d",&b);  //Lee el segundo mumero
	scanf("%d",&c);  //Lee el tercer numero
	//Si el primer numero es mayor al segundo
	if(a>b)
	{
		//Si el primer numero es mayor al tercero
		if(a>c)
		{
			//Si el segundo numero es mayor al tercero
			if(b>c)
			{
				printf("%d,%d,%d\n",a,b,c);  //Se muestran las salidas del algoritmo
			}
			//Si el tercer numero es mayor al segundo
			else
			{
				printf("%d,%d,%d\n",a,c,b);  //Se muestran las salidas del algoritmo
			}
		}
		//Si el tercer numero es mayor al primero 
		else
		{
			printf("%d,%d,%d\n",c,a,b);	 //Se muestran las salidas del algoritmo	  	
		}
	}
	//Si el segundo numero es mayor al primero
	else
	{
		//Si el segundo numero es mayor al tercero 
		if(b>c)
		{
			//Si el primer numero es mayor al tercero
			if(a>c)
			{
				printf("%d,%d,%d\n",b,a,c);  //Se muestran las salidas del algoritmo
			}
			//Si el tercer numero es mayor al primero 
			else
			{
				printf("%d,%d,%d\n",b,c,a);  //Se muestran las salidas del algoritmo
			}
		}
		//Si el tercer numero es mayor al segundo
		else
		{
			printf("%d,%d,%d\n",c,b,a);  //Se muestran las salidas del algoritmo
		}
	}
	system("pause");  //Nos permite ver el resultado en la pantalla hasta que demos enter
}
