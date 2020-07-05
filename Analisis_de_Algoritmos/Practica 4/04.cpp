#include<stdio.h>
#include<stdlib.h>

void Merge(int *A, int *I, int izquierda, int *D, int derecha) {
	int i, j, k;
	i = 0, k = 0;
	A[k] = I[i];
	k++;
	while(i < izquierda){
		for(j = 0; j < derecha; j++){
			if (I[i] == D[j]){
				D[j] = 0;
				A[k] = D[j];
				k++;
			}
			else {
				A[k] = D[j];
				k++;
			}
		}
		i++;
	}
	
}

void MergeSort(int *A, int n) {
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
	printf("Ingrese el tamaño del arreglo:\n");
	scanf("%d", &n);
	int A[n];
	printf("Ingrese los valores del arreglo: \n");
	for (int i = 0; i < n; i++){
		 scanf("%d",&valor);
		 A[i] = valor;
	} 
	
	int i, elementos;

	elementos = sizeof(A)/sizeof(A[0]); 
	MergeSort(A, elementos);
	printf("El arreglo resultante es: \n");
	for(i = 0;i < elementos;i++)
		if (A[i] != 0) 
			printf("%d ",A[i]);
	return 0;
}
