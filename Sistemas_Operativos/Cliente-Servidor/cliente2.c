#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>		//servidor de la memoria compartida
#include <sys/ipc.h>		//ejecutar el servidor antes de ejecutar al cliente
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>		
#include <unistd.h>

#define TAM_MEM 150
#define TAM 10

int main(int argc, char const *argv[]){
	int  matriz1[TAM][TAM], matriz2[TAM][TAM], matriz[TAM][TAM], i=0, j=0;
	int shmid2, shmidSum;
	key_t llave2, llaveSum;
	llave2 = 5679;
	llaveSum = 5680;
	int *shm2, *m2, *shmSum, *resSum;
	
	//recibir la matriz 1
	shmid2=shmget(llave2, TAM_MEM, 0666 | IPC_CREAT);
	if(shmid2==-1){
		perror("Error al obtener memoria compartida: shmget");
		exit(-1);
	}
	if((shm2=shmat(shmid2, NULL, 0))==(void*)-1){
		perror("Error al enlazar la memoria compartida: shmat");
		exit(-1);
	}

	m2 = shm2;
	//recibir matriz1 
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz1[i][j] = *m2;
			//printf("%d ", *m);
			*m2++;
		}
		//printf("\n");
	}

	//recibir la matriz 2
	//for(m=shm; *m!='\0'; m++){	
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz2[i][j] = *m2;
			//printf("%d ", *m);
			*m2++;
		}
		//printf("\n");
	}	

	//suma de matrices
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = matriz2[i][j]+matriz1[i][j];
		}
	}

	//Regresar el resultado
	shmidSum=shmget(llaveSum, TAM_MEM, 0666 | IPC_CREAT);
	if(shmidSum == -1){
		perror("*Error al obtener memoria compartida: shmget");
		return -1;
	}
	if((shmSum=shmat(shmidSum, NULL, 0))==(void*)-1){
		perror("*Error al enlazar la memoria compartida: shmat");
		exit(-1);
	}
	resSum = shmSum;		

	//printf("Copiando Resultado de la suma\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){	
			*resSum = matriz[i][j];
			//printf("%d ", *m);
			*resSum++;
		}
		//printf("\n");
	}

	//exit(0);
}