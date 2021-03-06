/*
Autor: Galindo Reyes Agustin
Grupo: 3CM9
Profesor: Jaime Hugo Puebla Lomas
Fecha: 24 de octubre del 2018
Descripcion: En el siguiente programa calculamos la transformada discreta de fourier (DTF) a partir de los valores que el usuario introduce
Vresion: 1.1
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <complex.h>

double complex operacion(int a, int b, int c, int d){
 	double PI = acos(-1); //Definimos el valor de PI
	double complex z = d*cexp((-2*I*PI*a*b)/c); //Conseguimos nuestro numero complejo utilizando la identidad de euler
	return(z); //Retornamos el numero complejo
}

void inicio (){
	int n, i, j, valor; 
	printf("Ingrese la cantidad de datos: \n");
	scanf("%d", &n);
	int datos[n]; //Arreglo que almacenara los valores que introduzca el usuario
	printf("Ingrese los valores del arreglo: \n");
	for (int l = 0; l < n; l++){ //El for que nos permite llenar el arreglo con los valores que introduce el usuario
		 scanf("%d",&valor);
		 datos[l] = valor;
	}
	double complex resultado[n]; //Creamos un arreglo complejo para almacenar los resultados de la DTF
	for(i=0; i<n; i++){
		resultado[i] = 0.0 + 0.0 * I; //Asignamos un numero complejo inicial a cada espacio del arreglo complejo
		for(j=0; j<n; j++){
			resultado[i] = resultado[i]+operacion(i, j, n, datos[j]); //Sumamos el valor almacenado en el arreglo mas el resultado de la funcion operacion
		}
	}
	printf("Los resultados son: \n");
	for(i=0; i<n; i++){ //El for que imprime los resultados de la DTF
		double complex z = resultado[i];
		printf("F[%i]= 1/%d [% .2f%+.2fi]\n", i, n, creal(z), cimag(z));
	}
}

int main (){
	printf("BIENVENIDO A LA TRANSFORMADA DISCRETA DE FOURIER \n\n");
	char opcion;
	do{
		inicio();
		printf("\nDesea Continuar \n");
		printf("Si ------> S \n");
		printf("No ------> N \n");
		fflush(stdin);
		scanf("%c", &opcion);
		printf("\n");
	}while(opcion == 'S' || opcion == 's');
}