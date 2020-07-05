#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/time.h>

#define TAM 10

int main(int argc, char const *argv[]){
	int matriz1[TAM][TAM], matriz2[TAM][TAM], matriz[TAM][TAM], archivo, i, j, k=0;
	char matrizc[TAM][TAM];
	char *resultado = (char*)malloc(sizeof(char));

	printf("Resta de matrices\n");
	//abrir matriz 1
	archivo = open("matriz1.txt", O_RDWR);		
	read(archivo, resultado, 150);
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j] = resultado[k];
			//printf("%c ", resultado[k]);
			k++;
		}
		//printf("\n");
	}
	close(archivo);
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matriz1[i][j] = (int) (matrizc[i][j]-48);
			//printf("%d ", matriz1[i][j]);
		}
		//printf("\n");
	}
	k=0;
	//abrir matriz 2
	archivo = open("matriz2.txt", O_RDWR);		
	read(archivo, resultado, 150);
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){	
			matrizc[i][j] = resultado[k];
			//printf("%d ", resultado[k]);
			k++;
		}
		//printf("\n");
	}
	close(archivo);
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matriz2[i][j] = (int) (matrizc[i][j]-48);
			//printf("%d ", matriz2[i][j]);
		}
		//printf("\n");
	}
	
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz1[j][i];
			//printf("%d ", matriz[i][j]);
		}
		//printf("\n");
	}
	
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]+48);
		}
	}
	archivo = creat("transpuesta1.txt", 0777);
	//printf("Archivo suma.txt creado\n");
	write(archivo, matrizc, sizeof(matrizc));							
	close(archivo);

	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz2[j][i];
			//printf("%d ", matriz[i][j]);
		}
		//printf("\n");
	}
	
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]+48);
		}
	}
	archivo = creat("transpuesta2.txt", 0777);
	//printf("Archivo suma.txt creado\n");
	write(archivo, matrizc, sizeof(matrizc));							
	close(archivo);
}