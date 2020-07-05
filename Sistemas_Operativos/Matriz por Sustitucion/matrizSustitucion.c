#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <sys/time.h>

#define TAM 10

int main(){
	pid_t id, id2;

	char *argv[7];
	argv[0]="sumaM";
	argv[1]="restaM";
	argv[2]="multiplicacion";
	argv[3]="transpuestaM";
	argv[4]="inversaM";
	argv[5]="leer";
	argv[6]=NULL;
	
	int i, j, archivo;
	char matrizc [TAM][TAM];
	char matriz1[TAM][TAM], matriz2[TAM][TAM];
	
	printf("Matriz 1:\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			printf("Ingrese el dato[%d][%d]: ", i, j);
			fflush(stdin);
			scanf("%c", &matriz1[i][j]);
			getchar();
		}
	}		
	/*for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz1[i][j]);
		}
	}*/
	archivo = creat("matriz1.txt", 0777);
	write(archivo, matriz1, sizeof(matriz1));
	close(archivo);

	printf("Matriz 2:\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			printf("Ingrese el dato[%d][%d]: ", i, j);
			fflush(stdin);
			scanf("%c", &matriz2[i][j]);
			getchar();
		}
	}
/*	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			matrizc[i][j]= (char)(matriz2[i][j]-48);
		}
	}*/
	archivo = creat("matriz2.txt", 0777);
	write(archivo, matriz2, sizeof(matriz2));
	close(archivo);


	printf("Resultados:\n");
	printf("Matriz 1: \n");
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			printf("%c ", matriz1[i][j]);
		}
		printf("\n");
	}
	//matriz 2
	printf("Matriz 2: \n");
	for (i=0; i<TAM; i++){
		for (j=0; j<TAM; j++){
			printf("%c ", matriz2[i][j]);
		}
		printf("\n");
	}
	if((id=fork())==0){
		//crear los procesos
		for(i=0; i<6; i++){
			if((id2=fork())==0){
				if((execv(argv[i], argv)) == -1)
					printf("No se pudo crear el programa %s\n", argv[i]);
				exit(0);
			}
			wait(&id2);
		}
		exit(0);
	}
	else{
		wait(&id);
		printf("Fin\n");
		exit(0);
	}
}