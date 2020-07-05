#include<stdio.h>
#include<stdlib.h>

void Merge(int *A,int *L,int leftCount,int *R,int rigthCount) {
	int i,j,k;
	i = 0; j = 0; k =0;
	for(i = 0;i < leftCount;i++) printf("%d ",L[i]);
	printf("\n");
	for(i = leftCount;i < rigthCount;i++) printf("%d ",R[i]);
	printf("\n");

	while(i<leftCount && j< rigthCount) {
		if(L[i] > R[j]) {
		//A[k] = L[i];
		printf("%d, %d \n", i, j);
		j++;
		k++;
		//printf("%d ", A[k]);
		}//A[k++] = L[i++];
		else {
		//A[k] = R[j];
		//printf("%d, %d ", i, j);
		i++;
		k++;
		//printf("%d ", A[k]);
		}//A[k++] = R[j++];
	}
	while(i < leftCount){
		//A[k] = L[i];
		//printf("%d ", A[k]);
		i++;
		k++;
		//printf("%d ", A[k]);
	} 
	while(j < rigthCount) {
	//A[k] = R[j];
	//printf("%d ", A[k]);
	j++;
	k++;
	//printf("%d ", A[k]);
	}
	//A[k++] = R[j++];
}

void MergeSort(int *A,int n) {
	int mid,i, *L, *R;
	if(n < 2) return; 

	mid = n/2;  
	L = (int*)malloc(mid*sizeof(int)); 
	R = (int*)malloc((n- mid)*sizeof(int)); 
	
	for(i = 0;i<mid;i++) L[i] = A[i]; 
	for(i = mid;i<n;i++) R[i-mid] = A[i]; 

	MergeSort(L,mid); 
	MergeSort(R,n-mid);
	Merge(A,L,mid,R,n-mid);  
        free(L);
        free(R);
}

int main() {
	
	int n, valor, aux;
	scanf("%d", &n);
	int A[n];
	for (int i = 0; i < n; i++){
		 scanf("%d",&valor);
		 A[i] = valor;
	} 
	
	int i,numberOfElements;

	numberOfElements = sizeof(A)/sizeof(A[0]); 
	MergeSort(A,numberOfElements);
	
	//for(i = 0;i < numberOfElements;i++) printf("%d ",A[i]);
	return 0;
}

