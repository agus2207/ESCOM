/*
Autor: Galindo Reyes Agustin
Grupo: 3CM9
Profesor: Jaime Hugo Puebla Lomas
Fecha: 10 de Nociembre del 2018
Descripcion: Programa que realiza la convolucion discreta de una respuesta impulsional de un sistema LIT con una señal de entrada, los cuales son
introducidos por el usuario, estableciendo el origen de cada uno. El usuario es capaz de introducir las muestra que el necesite y el programa
le dara la salida de respuesta.
Version: 1.1
*/

#include <stdio.h>
#include <stdlib.h>

void resultado(int a[], int b[], int n){
	printf("La convolucion es igual a: \n");
	for(int i = 0; i <n; i++){
		printf("Y[%d] = %d\n", b[i], a[i]);
	}
}

void imprimir(int a[], int b, int c){ //Funcion que imprime los arreglos
	for(int i = 0; i <b; i++){
		printf("A[%d] = %d\n", c, a[i]);
		c=c+1;
	}
}

int convolucion(int LIT[], int x[], int n, int d, int taml, int tamx, int negl, int negx){ //Funcion que relaiza la convolucion discreta
	int des, res; //Variables que guardaran el resultado y el desplazamiento en la LIT invertida
	res=0;
	int espejo[taml]; //Declaramos un arreglo donde se almacenara la inversion de LIT
	negl=(-1)*negl+1; //Volvemos positivo el valor mas pequeño en el eje de las x de LIT
	for(int i=0; i<taml; i++){ //Invertimos LIT
		espejo[taml-i-1]=LIT[i]; //El arreglo que almacena la inversion de LIT se llama espejo
		negl=negl-1; //Invertimos todos los valores del eje de x en LIT
	}
	des=negl+d; //Le sumamos el desplazamiento al valor mas pequeño de la LIT invertida
	if(taml>tamx || taml == tamx){ //Si los tamaños de x y LIT coinciden o el tamaño de LIT es mas grande que el de x entramos a esta condicional
		int j=0; //Variable auxiliar para recorrer los arreglos
		for(int i=0; i<taml; i++){ //Recorremos el arreglo de mayor tamaño
			if(negx == des){ //Si ambos arreglos tienen el mismo inicio en el eje x entramos a esta condicional
				if(j>=tamx || i>=taml) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else //Si no al resultado le sumamso la multiplicacion del valor de la LIT invertida en la posicion i y el valor de x en la posicion j
					res = res+(espejo[i]*x[j]);
				negx=negx+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				des=des+1; // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida
			}
			else if (negx > des){ //Si la posicion de x[n] no coincide con la de la LIT invertida entramos a esta condicional
				do{
					des=des+1; //Incrementamos la posicion de la LIT invertida hasta que coincida con la de x[n]
					i++;
				}while(negx != des);
				if(j>=tamx || i>=taml) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else //Si no al resultado le sumamso la multiplicacion del valor de la LIT invertida en la posicion i y el valor de x en la posicion j
					res = res+(espejo[i]*x[j]);
				negx=negx+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				des=des+1; // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida 
			}
			else if (des > negx){ //Si la posicion de la LIT invertida no coincide con la señal de entrada entramos a esta condicional
				do{
					negx=negx+1; //Incrementamos la posicion de la señal de entrada invertida hasta que coincida con la de x[n]
					j++;
				}while(des != negx);
				if(j>=tamx || i>=taml) //Si j sobrepasa el tamaño de la LIT o i sobrepasa el tamaño de la LIT invertida al resultado le sumamos 0
					res = res+0;
				else
					res = res+(espejo[i]*x[j]);
				negx=negx+1; //Recorremos el ultimo valor usado en el eje x de x[n]
				des=des+1;  // Recorremos el ultimo valor usado en el eje de las x en la LIT invertida
			}
			j++; //Aumentamos j que represenata la posicion de la señal de entrada
		}
	}
	else if(tamx>taml){ //Si la señal de entrada es mayor a la LIT invertida entra a esta condicional y se repiten las operaciones de la condicional anterior
		int j=0;
		for(int i=0; i<tamx; i++){
			if(negx == des){
				if(j>=taml || i>=tamx)
					res = res+0;
				else
					res = res+(espejo[i]*x[j]);
				negx=negx+1;
				des=des+1;
			}
			else if (negx > des){
				do{
					des=des+1;
					j++;
				}while(negx != des);
				if(j>=taml || i>=tamx)
					res = res+0;
				else
					res = res+(espejo[i]*x[j]);
				negx=negx+1;
				des=des+1;
			}
			else if (des > negx){
				do{
					negx=negx+1;
					i++;
				}while(des != negx);
				if(j>=taml || i>=tamx)
					res = res+0;
				else
					res = res+(espejo[i]*x[j]);
				negx=negx+1;
				des=des+1;
			}
			j++;
		}
	}
	return res;
}

void llenar (int a[], int b){ //Funcion que llena los arreglos
	int valor;
	for (int i = 0; i < b; i++){ 
		 scanf("%d",&valor);
		 a[i] = valor;
	}
}

void inicio(){ //Funcion que inicia el programa
	int n, size1, size2, des, neg1, neg2;
	printf("Ingrese la cantidad de muestras: \n"); //Se pide la cantidad de muestras
	scanf("%d", &n);
	printf("Ingrese el tamaño de h(n): \n"); //Se pide el tamaño de la LIT
	scanf("%d", &size1);
	int LIT[size1];
	printf("Ingrese los valores de h(n): \n"); //Se piden los datos de la LIT
	llenar(LIT, size1);
	printf("Ingrese el tamaño de la señal de entrada: \n"); //Se pide el tamaño de la señal de entrada
	scanf("%d", &size2);
	int x[size2];
	printf("Ingrese los datos de la señal de entrada: \n"); //Se piden los datos de la señal de entrada
	llenar(x, size2);
	printf("Ingrese el menor valor de h(n) en el eje x: \n"); //Donde inicia en el eje de las x la LIT
	scanf("%d", &neg1);
	printf("Ingrese el menor valor de la señal de entrada en el eje x: \n"); //Donde inicia en el eje de las x la señal de entrada
	scanf("%d", &neg2);
	int res[n]; 
	int desp[n];
	printf("La respuesta impulsional de un sistema LIT es: \n"); //Se imprime LIT en pantalla
	imprimir(LIT, size1, neg1);
	printf("Señal de entrada: \n"); //Se imprime la señal de entrada en pantalla
	imprimir(x, size2, neg2);
	for(int i=0; i<n; i++){ //Recorre y llena el arreglo de la respuestas de la convolucion
		printf("Ingrese el desplazamiento %d: \n", (i+1)); //Se pide la cantidad de desplazamientos que se le haran a la LIT invertida
		scanf("%d", &des);
		res[i]= convolucion(LIT, x, n, des, size1, size2, neg1, neg2); //Manda a llamar a la funcion concolucion para calcular la respuesta
		printf("y[%d] = %d \n", des, convolucion(LIT, x, n, des, size1, size2, neg1, neg2)); //Se muestra la respuesta en pantalla
		desp[i] = des;
	}
	resultado(res, desp, n);
}

int main (){ //Menu del programa
	printf("BIENVENIDO A LA CONVOLUCION DISCRETA \n\n");
	char opcion;
	do{ //do while para que el programa finalice cuando el usuario precione una tecla diferente a S
		inicio();
		printf("\nDesea Continuar \n");
		printf("Si ------> S \n");
		printf("No ------> N \n");
		fflush(stdin);
		scanf("%c", &opcion);
		printf("\n");
	}while(opcion == 'S' || opcion == 's');
	
}
