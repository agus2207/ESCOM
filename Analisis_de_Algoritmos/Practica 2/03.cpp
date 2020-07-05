#include <stdio.h>
#include <stdlib.h>

int main(){
	int n, valor, aux;
	scanf("%d", &n);
	int A[n];
	for (int i = 0; i < n; i++){
		 scanf("%d",&valor);
		 A[i] = valor;
	}
	for (int j = 1; j < n; j++){
		for (int k = 0; k < n - j; k++){
			if(A[k] > A[k+1]){
			aux = A[k];
			A[k] = A[k+1];
			A[k+1] = aux;
			}
		}
	}
	for (int l = 0; l < n; l++){
		printf("\n A[%d]=%d",l,A[l]);
	}	
}
