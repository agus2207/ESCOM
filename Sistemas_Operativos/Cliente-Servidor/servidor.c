#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>		//servidor de la memoria compartida
#include <sys/ipc.h>		//ejecutar el servidor antes de ejecutar al cliente
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>		
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>
#include <string.h>

#define TAM_MEM 150
#define TAM 10

void inversa(int matriz[TAM][TAM], char *nombre);
double Determinante (double m1[10][10],int n);
void matrizCofactores (double m1[10][10], int n, double determinante, char* nombre);
void crearArchivo( char* resultado , char* nombre);

int main(){
	int  matriz[TAM][TAM], i=0, j=0;
	int m1[TAM][TAM], m2[TAM][TAM];
	int *shm, *m;
	char *argv[2];
	argv[0] = "cliente1";
	argv[1] = NULL;
	if(fork() == 0){ //proceso padre
		
		int shmid;
		key_t llave;
		llave = 5678;
		for(i=0; i<TAM; i++){
			for (j=0; j<TAM; j++){
				m1[i][j]=rand()%10;
				m2[i][j]=rand()%10;
			}
		}

		printf("Matriz 1:\n");
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++)
				printf("%d ", m1[i][j]);
			printf("\n");
		}
		printf("Matriz 2:\n");	
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++)
				printf("%d ", m2[i][j]);
			printf("\n");
		}

		//compartir memoria de m1
		shmid=shmget(llave, TAM_MEM, 0666 | IPC_CREAT);
		if(shmid == -1){
			perror("*Error al obtener memoria compartida: shmget");
			return -1;
		}
		if((shm=shmat(shmid, NULL, 0))==(void*)-1){
			perror("*Error al enlazar la memoria compartida: shmat");
			exit(-1);
		}
		m = shm;		

		//printf("Copiando M1\n");
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++){	
				*m = m1[i][j];
				//printf("%d ", *m);
				*m++;
			}
			//printf("\n");
		}

		//printf("Copiando M2\n");
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++){	
				*m = m2[i][j];
				//printf("%d ", *m);
				*m++;
			}
			//printf("\n");
		}

		printf("\nMultiplicacion de Matrices:\n");
		//recibir la matriz suma
		int *shmMul, shmidMul,  *resMul;
		key_t llaveMul;
		llaveMul = 5681;
		shmidMul=shmget(llaveMul, TAM_MEM, 0666 | IPC_CREAT);
		if(shmidMul==-1){
			perror("Error al obtener memoria compartida: shmget");
			exit(-1);
		}
		if((shmMul=shmat(shmidMul, NULL, 0))==(void*)-1){
			perror("Error al enlazar la memoria compartida: shmat");
			exit(-1);
		}


		resMul = shmMul;
		//recibir matriz1 
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++){
				matriz[i][j] = *resMul;
				//printf("%d ", *m);
				*resMul++;
			}
			//printf("\n");
		}
		
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++)
				printf("%d ", matriz[i][j]);
		printf("\n");
		}
		inversa(matriz, "multiplicacion.txt");

		printf("\nSuma de Matrices:\n");
		//recibir la matriz suma
		int *shmSum, shmidSum,  *resSum;
		key_t llaveSum;
		llaveSum = 5680;
		shmidSum=shmget(llaveSum, TAM_MEM, 0666 | IPC_CREAT);
		if(shmidSum==-1){
			perror("Error al obtener memoria compartida: shmget");
			exit(-1);
		}
		if((shmSum=shmat(shmidSum, NULL, 0))==(void*)-1){
			perror("Error al enlazar la memoria compartida: shmat");
			exit(-1);
		}


		resSum = shmSum;
		//recibir matriz1 
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++){
				matriz[i][j] = *resSum;
				//printf("%d ", *m);
				*resSum++;
			}
			//printf("\n");
		}
		
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++)
				printf("%d ", matriz[i][j]);
			printf("\n");
		}
		inversa(matriz, "suma.txt");

		//crear el hijo cliente
		if((execv(argv[0], argv)) == -1)
			printf("No se pudo crear el programa %s\n", argv[0]);


		exit(0);
	}
}


void inversa(int matriz[TAM][TAM], char *nombre){
	//inversa

	double determinante;
   	double inv1[TAM][TAM]; 
   	int i, j;
   	for(i = 0 ; i < TAM ; i++ )
   	{
       	for(j = 0 ; j < TAM ; j++ )
       	{
        	inv1[i][j] = matriz[i][j];
       	}   
   	}
   	determinante = Determinante (inv1, 10);
    if (determinante != 0)
    {
        matrizCofactores(inv1, 10, determinante, nombre);
    }
    else
    {
        printf("\nLa matriz no tiene inversa\n");
    }
}

double Determinante (double m1[10][10],int n)
{
  double s = 1, det = 0;
  int i, j, k, m, x;
  double m2[10][10];
  if (n == 1)
    return m1[0][0];
  else
  {
    for (k = 0; k < n; k++)
    {
      m = 0;
      x = 0;
      for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
        {
          m2[i][j] = 0;
          if (i != 0 && j != k)
          {
            m2[m][x] = m1[i][j];
            if (x < (n - 2))
              x++;
            else
            {
              x = 0;
              m++;
            }
          }
        }
      det = det + s * (m1[0][k] * Determinante(m2, n-1));
      s *= -1;
  }
  return det;
    }
}

void matrizCofactores (double m1[10][10], int n, double determinante, char* nombre)
{
  char resultado[700];
  char buffer[8]={};
  int q, m, i, j, k, l;
  double m2[10][10], matrizFactores[10][10], aux[10][10], inversa[10][10];
  for (l = 0; l < n; l++)
  {
    for (k = 0; k < n; k++)
    {
      m = 0; q = 0;
      for (i = 0; i < n; i++)
      {
        for (j = 0; j < n; j++)
        {
          if ( i != l && j != k)
          {
            m2[m][q] = m1[i][j];
            if (q < (n-2))
            {
              q++;
            }
            else 
            {
                q = 0; m++;
            }
          }
        }
      }
      matrizFactores[l][k] = (pow(-1, l + k)* Determinante (m2, n-1));
    }
  }
  for (i = 0; i < 10; i++)
  {
    for (j = 0; j < 10; j++)
    {
      aux[i][j] = matrizFactores[j][i];
    }
  }
  for (i = 0; i < 10; i++)
  {
    for (j = 0; j < 10; j++)
    {
      inversa[i][j] = aux[i][j] / determinante;
    }
  }
 
  for (i = 0; i < 10; i++)
  {
    for (j = 0; j < 10; j++)
    {
      sprintf(buffer,"%.3lf\t",inversa[i][j]);
      strcat(resultado,buffer);
      memset(buffer,0,8);
    }
    strcat(resultado,"\r\n");
  }
   crearArchivo(resultado, nombre);
}

void crearArchivo( char* resultado , char* nombre)
{
	int i, j, archivo;
	printf("\nMatriz inversa\n");
	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			printf("%d ", resultado[i]);
		}
		printf("\n");
	}

	archivo = creat(nombre, 0777);
	write(archivo, resultado, sizeof(resultado));
	close(archivo);	
}
