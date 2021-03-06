#include<stdio.h>
#include<stdlib.h>

char * primo (int numero){
	int i=0;
	for (i=2; i<numero; i++){
		if(numero%i==0) return "No es primo";
	}	
	return "Es primo\n";
}

int main(int argc, char * argv[]){
	if(argc < 2){
		printf("Uso cprimo.out < numero >\n");
		return 0;
	}	
	unsigned int numero;
	numero = atoi(argv[1]);
	printf ("%s",primo(numero));		
}
