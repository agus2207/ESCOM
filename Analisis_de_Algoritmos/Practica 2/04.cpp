#include <stdio.h>
#include <stdlib.h>
#define n

int merge(int A[n]){
	if(sizeof A == 1){
		for (int l = 0; l < n; l++){
		printf("\n A[%d]=%d",l,A[l]);
	}
	}	
	else {
		int a1[n/2];
		for (int i = 0; i < n/2; i++){
			a1[i] = A[i];
		}
		int a2[n/2];
		for (int i = n/2 + 1; i < n; i++){
			a2[i] = A[i];
		}
		a1[n/2] = merge(a1[n/2]);
		a2[n/2] = merge(a2[n/2]);
		mergesort (a1, a2);	
	}	
}

int mergesort(int A[n], int B[m]){
	int C[];
	while(sizeof A == 0 || sizeof B == 0){
		if(A[0] > B[0]){
			B[0] = arrayc(B[0]);
		}
		else{
			A[0] = arrayc(A[0]);
		}
	}
	while(sizeof A == 0){
		A[0] = arrayc(A[0]);
	}
	while(sizeof A == 0){
		B[0] = arrayc(B[0]);
	}
}

int arrayc (int a){
	int C[n];
	for(int i = 0; i < n; i++){
		C[0] = a;
	}
	for (int l = 0; l < n; l++){
		printf("\n C[%d]=%d",l,C[l]);
	}
}

int main (){
	int valor, aux;
	scanf("%d", &n);
	int A[n];
	for (int i = 0; i < n; i++){
		 scanf("%d",&valor);
		 A[i] = valor;
	}
	A[n] = merge(A[n]);
}
