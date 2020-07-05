#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <math.h>

#define TAM 10
int matriz1[TAM][TAM], matriz2[TAM][TAM];

void *suma(void *arg);
void *resta(void *arg);
void *multi(void *arg);
void *transpuesta(void *arg);
void resultado(char* nombre);
void *mostrar(void *arg);

int main(void){
	int i, j;
	time_t t;
	srand((unsigned) time(&t));
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz1[i][j] = rand()%100;
			matriz2[i][j] = rand()%100;
		}
	}

	//suma
	pthread_t hiloSuma, hiloResta, hiloMulti, hiloTrans, hiloResul;
	pthread_create(&hiloSuma, NULL, (void*)suma, NULL);
	pthread_join(hiloSuma, NULL);
	//resta
	pthread_create(&hiloResta, NULL, (void*)resta, NULL);
	pthread_join(hiloResta, NULL);
	//Multiplicacion
	pthread_create(&hiloMulti, NULL, (void*)multi, NULL);
	pthread_join(hiloMulti, NULL);
	//Transouesta
	pthread_create(&hiloTrans, NULL, (void*)transpuesta, NULL);
	pthread_join(hiloTrans, NULL);
	//resultados
	pthread_create(&hiloResul, NULL, (void*)mostrar, NULL);
	pthread_join(hiloResul, NULL);
	return 0;
}

void *suma(void *arg){
	int matriz[TAM][TAM], i, j, archivo;
	char matrizc[TAM][TAM];
	printf("Suma de matrices\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz2[i][j]+matriz1[i][j];
		}
	}
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]);
			//printf("%d ", matrizc[i][j]);
		}
		//printf("\n");
	}
		archivo = creat("suma.txt", 0777);
		//printf("Archivo suma.txt creado\n");
		write(archivo, matrizc, sizeof(matrizc));							
		close(archivo);
		printf("Hilo suma terminado\n");
		return NULL;
}

void *resta(void *arg){
	int matriz[TAM][TAM], i, j, archivo;
	char matrizc[TAM][TAM];
	printf("Resta de matrices\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz1[i][j]-matriz2[i][j];
		}
	}
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]);
			//printf("%d ", matrizc[i][j]);
		}
		//printf("\n");
	}
	archivo = creat("resta.txt", 0777);
	write(archivo, matrizc, sizeof(matrizc));							
	close(archivo);
	printf("Hilo resta terminado\n");
	return NULL;
}

void *multi(void *arg){
	int matriz[TAM][TAM], i, j, k, archivo, operacion;
	char matrizc[TAM][TAM];
	printf("Multiplicacion de matrices\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			operacion = 0;
			for(k=0; k<TAM; k++){
				operacion = operacion + (matriz1[i][k]*matriz2[k][j]);
				matriz[i][j] = operacion;
			}
		}
	}
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]);
			//printf("%d ", matrizc[i][j]);
		}
		//printf("\n");
	}
	//printf("Proceso Archivo multiplicacion.txt creado\n");
	archivo = creat("multiplicacion.txt", 0777);
	write(archivo, matrizc, sizeof(matrizc));							
	close(archivo);
	printf("Hilo multiplicacion terminado\n");
	return NULL;
}

void *transpuesta(void *arg){
	int matriz[TAM][TAM], i, j, archivo, operacion;
	char matrizc[TAM][TAM];
	printf("Transpuesta de matrices\n");
	//printf("Proceso Transpuesta de matrices\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz1[j][i];
		}
	}
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]);
			//printf("%d ", matrizc[i][j]);
		}
		//printf("\n");
	}
	archivo = creat("transpuesta.txt", 0777);
	write(archivo, matrizc, sizeof(matrizc));	
	close(archivo);
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz2[j][i];						
		}
	}
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz[i][j]);
			//printf("%d ", matrizc[i][j]);
		}
		//printf("\n");
	}
	archivo = creat("transpuesta2.txt", 0777);
	write(archivo, matrizc, sizeof(matrizc));
	//	printf("Archivo transpuesta.txt creado\n");
	close(archivo);
	printf("Hilo transpuesta terminado\n");
}

void *mostrar(void *arg){
	int i, j;
	printf("Mostrar resultados\n");
	printf("Matriz 1: \n");
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			printf("%d ", matriz1[i][j]);
		}
		printf("\n");
	}
	//matriz 2
	printf("Matriz 2: \n");
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			printf("%d ", matriz2[i][j]);
		}
		printf("\n");
	}
	printf("\nSuma: \n");
	resultado("suma.txt");
	printf("\nResta: \n");
	resultado("resta.txt");
	printf("\nMultiplicacion: \n");
	resultado("multiplicacion.txt");
	printf("\nTranspuesta Matriz 1: \n");
	resultado("transpuesta.txt");
	printf("\nTranspuesta Matriz 2: \n");
	resultado("transpuesta2.txt");
}

void resultado(char* nombre){
	char *resultado = (char*)malloc(sizeof(char));
	//char *aux = (char*)malloc(sizeof(char));
	int i, j, k=0, archivo;
	archivo = open(nombre, O_RDWR);		
		read(archivo, resultado, 150);
		for (i=0; i<TAM; i++){
			for (j=0; j<TAM; j++){
				if(resultado[k]<0)
					printf("%d ", resultado[k]+256);
				else
					printf("%d ", resultado[k]);
				k++;
			}
			printf("\n");
		}
		close(archivo);
}