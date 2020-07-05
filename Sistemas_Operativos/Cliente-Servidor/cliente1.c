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
	int  matriz1[TAM][TAM], matriz2[TAM][TAM], matriz[TAM][TAM], i=0, j=0, k, operacion;
	int shmid, shmid2, shmidMul;
	key_t llave, llave2, llaveMul;
	llave = 5678;
	llaveMul = 5681;
	int *shm, *m, *shm2, *m2, *shmMul, *resMul;
	char *arg[2];
	arg[0] = "cliente2";
	arg[1] = NULL;

	//recibir la matriz 1
	shmid=shmget(llave, TAM_MEM, 0666 | IPC_CREAT);
	if(shmid==-1){
		perror("Error al obtener memoria compartida: shmget");
		exit(-1);
	}
	if((shm=shmat(shmid, NULL, 0))==(void*)-1){
		perror("Error al enlazar la memoria compartida: shmat");
		exit(-1);
	}

	m = shm;
	//recibir matriz1 
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz1[i][j] = *m;
			//printf("%d ", *m);
			*m++;
		}
		//printf("\n");
	}

	//recibir la matriz 2
	//for(m=shm; *m!='\0'; m++){	
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz2[i][j] = *m;
			//printf("%d ", *m);
			*m++;
		}
		//printf("\n");
	}

	//Multiplicacion
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			operacion = 0;
			for(k=0; k<TAM; k++){
				operacion = operacion + (matriz1[i][k]*matriz2[k][j]);
				matriz[i][j] = operacion;
			}
		}
	}

	llave2 = 5679;
	//compartir memoria de m1
	shmid2=shmget(llave2, TAM_MEM, 0666 | IPC_CREAT);
	if(shmid2 == -1){
		perror("*Error al obtener memoria compartida: shmget");
		return -1;
	}
	if((shm2=shmat(shmid2, NULL, 0))==(void*)-1){
		perror("*Error al enlazar la memoria compartida: shmat");
		exit(-1);
	}
	m2 = shm2;		

	//printf("Copiando M1\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){	
			*m2 = matriz1[i][j];
			//printf("%d ", *m);
			*m2++;
		}
		//printf("\n");
	}

	//printf("Copiando M2\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){	
			*m2 = matriz2[i][j];
			//printf("%d ", *m);
			*m2++;
		}
		//printf("\n");
	}

	//Regresar el resultado
	shmidMul=shmget(llaveMul, TAM_MEM, 0666 | IPC_CREAT);
	if(shmidMul == -1){
		perror("*Error al obtener memoria compartida: shmget");
		return -1;
	}
	if((shmMul=shmat(shmidMul, NULL, 0))==(void*)-1){
		perror("*Error al enlazar la memoria compartida: shmat");
		exit(-1);
	}
	resMul = shmMul;		

	//printf("Copiando Resultado de la suma\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){	
			*resMul = matriz[i][j];
			//printf("%d ", *m);
			*resMul++;
		}
		//printf("\n");
	}


		//crear el hijo cliente
		if((execv(arg[0], arg)) == -1)
			printf("No se pudo crear el programa %s\n", arg[0]);

	//exit(0);
}