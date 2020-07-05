#include<stdio.h>
#include<stdlib.h>
#include <math.h>

int main (){
	
	int n, valor, c;
	printf("Introduzca el tamaño del arreglo: \n");
	scanf("%d", &n);
	int A[n];
	printf("Introduzca los valores del arreglo: \n");
	for (int i = 0; i < n; i++){
		scanf("%d",&valor);
		A[i] = valor;
	}
		c = pow(n,2);
		int i = 0;
		printf("Las pociciones que coinciuden con el contenido son: \n");
		while (c > 0){
			if (A[i] == i)
				printf("%d ", i);
			i++;
			c = c/2;
		}
	
}
