#include <stdio.h>
#include <stdlib.h>

int main(void){
	int n, p=0, e;
 	scanf("%d", &n);
 	int l[n];
 		for (int i=0; i<n; i++){
 		scanf("%d", &e);
 		l[i]= e;		
	}
	for(int j = 0; l[j]; j++){
		for(int k=j+1; l[k]; k++){
			if(l[k]<l[j]){
				p=p+1;
			}
		}
	}
	printf("%d\n", p);
}
