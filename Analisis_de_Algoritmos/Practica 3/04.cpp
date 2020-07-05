#include<stdio.h>
#include<stdlib.h>

void Merge(int *A, int *I, int izquierda, int *D, int derecha) {
	int i,j,n;
	n = derecha;
	i = 0, j = 0;
	while(i < izquierda && j < derecha) {
		if(I[i] > D[j]) {
			printf("%d, %d \n", I[i], D[j]);
			j++;
		}
		else if (j == n-1){ 
			j = 0;
			i++;
		}
		else
			j++;
	}
	//j = 0;
	/*while(i < izquierda)
		i++; 
	while(j < derecha)
		j++;*/
}

void MergeSort(int *A,int n) {
	int mitad, i, *I, *D;
	if(n < 2) return; 

	mitad = n/2;  
	I = (int*)malloc(mitad*sizeof(int)); 
	D = (int*)malloc((n- mitad)*sizeof(int)); 
	
	for(i = 0; i < mitad; i++) 
		I[i] = A[i]; 
	for(i = mitad; i < n; i++) 
		D[i-mitad] = A[i]; 

	MergeSort(I, mitad); 
	MergeSort(D, n-mitad);
	Merge(A, I, mitad, D, n-mitad);  
        free(I);
        free(D);
}
 
int main() {
	
	int n, valor, aux;
	scanf("%d", &n);
	int A[n];
	for (int i = 0; i < n; i++){
		 scanf("%d",&valor);
		 A[i] = valor;
	} 
	
	int i, elementos;

	elementos = sizeof(A)/sizeof(A[0]); 
	MergeSort(A, elementos);
	return 0;
}
