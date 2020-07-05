
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <sys/time.h>

#define TAM 10

void mostrar(char* nombre);

int main(){
	printf("\nSuma: \n");
	mostrar("suma.txt");
	printf("\nResta: \n");
	mostrar("resta.txt");
	printf("\nMultiplicacion: \n");
	mostrar("multiplicacion.txt");
	printf("\nTranspuesta Matriz 1: \n");
	mostrar("transpuesta1.txt");
	printf("\nTranspuesta Matriz 2: \n");
	mostrar("transpuesta2.txt");
}

void mostrar(char* nombre){
	char *resultado = (char*)malloc(sizeof(char));
	//char *aux = (char*)malloc(sizeof(char));
	int matriz[TAM][TAM];
	int i, j, k=0, archivo;
	archivo = open(nombre, O_RDWR);		
	read(archivo, resultado, 150);
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matriz[i][j] = (int)(resultado[k]-48);
			k++;
		}
		printf("\n");
	}
	close(archivo);
	for(i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			if(matriz[i][j]<0)
				matriz[i][j]+=256;
		}
	}
	for(i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			printf("%d ", (matriz[i][j]));
		}
		printf("\n");
	}
}
