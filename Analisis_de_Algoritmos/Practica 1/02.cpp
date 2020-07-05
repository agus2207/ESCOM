#include <stdio.h>
#include <stdlib.h>

int main (){
	int n, valor, s = 0;
	printf("Ingrese el tamaño del arreglo:\n");
	scanf("%d", &n);
	int A[n];
	printf("Ingrese los valores del arreglo: \n");
	for (int l = 0; l < n; l++){
		 scanf("%d",&valor);
		 A[l] = valor;
	}
	int B[n][n];
	printf("\nLos valores de su matriz son los siguientes: \n");
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			if (i < j){
				for(int k = i; k < j+1; k++){
					s = s + A[k];
				}
				B[i][j] = s;
				s = 0;
			}
			else{
				B[i][j] = 0;
			}
			printf("\n B[%d][%d]=%d",i,j,B[i][j]);
		}
	}
} 
