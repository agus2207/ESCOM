/*
AUTOR: Agustin Galindo Reyes       Agosto 2016

DESCRIPCION: Programa solucion al problema 4: "Realizar un programa que calcule el cubo de una serie de numeros positivos introducidos 
uno a uno, si se introduce un numero negativo se debera terminar el programa. El primer numero a introducir nunca sera negativo". El
programa se base en el pseudocodigo que nos otorgo el profesor.
 
OBSERVACIONES:
El programa recibe una serie numeros enteros, el programa no funcionara de manera correcta si se introducen caracteres, cadenas o 
numeros flotantes, de ignorar esto se creara un bucle. El programa finalizara hasta introducir un numero negativo entero.

COMPILACION: gcc programa.cpp
EJECUCIOM: Programa.exe (En Windows)
*/

//LIBRERIAS
#include<stdio.h>  //Incluye las funciones estandar de entrada y salida
#include<stdlib.h>  //Incluye las funciones de busqueda y ordenamiento de datos

//PROGRAMA PRINCIPAL
int main (void)
{
	int numero, cubo;  //Almacena el numero otorgado y su cubo
	//Realizaras las siguientes instrucciones
	do  
	{
		printf("Introduzca un numero entero\n");  //Imprime en la pantalla la leyenda "Introduzca un numero entero"
		scanf("%d",&numero);  //Lee el numero otorgado
		cubo=numero*numero*numero;  //una vez recibido el numero calcula su cubo
		printf("El cubo del numero es %d\n",cubo);  //Se muestran las salidas del algoritmo
	}
	//Mientras el numero sea mayor a 0
	while(numero>0);
	system("pause");  //Nos permite ver el resultado en la pantalla hasta que demos enter
    return 0;
}
