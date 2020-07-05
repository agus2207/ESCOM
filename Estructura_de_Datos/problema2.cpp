/*
AUTOR: Agustin Galindo Reyes       Agosto 2016

DESCRIPCION: Programa solucion al problema 2: "Se necesita realizar un programa que calcule el sueldo neto de un trabajador, 
el programa recibirá el sueldo base del  empleado y le descontara el impuesto sobre la renta". El programa se base en el 
diagrama de flujo que nos otorgo el profesor.
 
OBSERVACIONES:
El programa recibe el sueldo neto del trabajador, el programa no funcionara hasta insertar un numero mayor de 0, de lo contrario 
el programa finalizara.

COMPILACION: gcc programa.cpp
EJECUCIOM: Programa.exe (En Windows)
*/

//LIBRERIAS
#include<stdio.h>  //Incluye las funciones estandar de entrada y salida
#include<stdlib.h> //Incluye las funciones de busqueda y ordenamiento de datos

//PROGRAMA PRINCIPAL
int main(void)
{
	float sueldo_base;  //Almacena el sueldo base
	float sueldo_neto; //Almacena el sueldo base menos el descuento
	printf("Intoduzca el sueldo base\n"); //Imprime en la pantalla la leyenda "Introduzca el sueldo base"
	scanf("%f",&sueldo_base);  //Lee el sueldo base dado
	//Mientras el sueldo base sea mayor que 0
	while (sueldo_base>0)
	{
		//Si el sueldo base es menor o igual a 1000
		if(sueldo_base<=1000)
		{
			sueldo_neto=sueldo_base-(sueldo_base*0.05);  //El sueldo neto sera igual a el sueldo base menos el 5% del sueldo base
		    printf("El sueldo neto es igual a %f\n\n\n", sueldo_neto);  //Se muestran las salidas del algoritmo
		    break; //Rompe el bucle del while
		}
		//Si el sueldo es mayor a 1000
		else
		{
			//Si el sueldo base es mayor o igual a 1000 pero menor o igual a 5000
			if(1001<=sueldo_base<=5000)
			{
				sueldo_neto=sueldo_base-(sueldo_base*0.15);  //El sueldo neto sera igual a el sueldo base menos el 15% del sueldo base
		        printf("El sueldo neto es igual a %f\n\n\n", sueldo_neto);  //Se muestran las salidas del algoritmo
				break; //Rompe el bucle del while			
			}
			//Si el sueldo base es mayor a 5000
			else
			{
				sueldo_neto=sueldo_base-(sueldo_base*0.30);  //El sueldo neto sera igual a el sueldo base menos el 30% del sueldo base
		        printf("El sueldo neto es igual a %f\n\n\n", sueldo_neto);  //Se muestran las salidas del algoritmo
		        break; //Rompe el bucle del while
			}
		}	
	}
    system("pause");  //Nos permite ver el resultado en la pantalla hasta que demos enter
}
