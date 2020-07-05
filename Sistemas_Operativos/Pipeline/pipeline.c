#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>

#define VALOR 1
#define TAM 10

void inversa(int matriz[TAM][TAM], char *nombre);
double Determinante (double m1[10][10],int n);
void matrizCofactores (double m1[10][10], int n, double determinante, char* nombre);
void crearArchivo( char* resultado , char* nombre);

int main(void){
	int desc_arch1[2], desc_arch2[2], desc_arch3[2], desc_arch4[2], rsum[2], rmul[2];
	int m1[TAM][TAM], m2[TAM][TAM], matriz[TAM][TAM], i, j;
	time_t t;
	srand((unsigned) time(&t));
	int id_proc0, id_proc1;

	for(i=0; i<TAM; i++){
		for(j=0; j<TAM; j++){
			matriz[i][j] = 0;
			m2[i][j] = 0;
			m1[i][j] = 0;
		}
	}


	//creacion de tuberias
	if(pipe(desc_arch1)!=0)
		exit(1);
	if(pipe(desc_arch2)!=0)
		exit(1);
	if(pipe(rsum)!=0)
		exit(1);
	if(pipe(rmul)!=0)
		exit(1);

	if((id_proc0 =fork())==0){	//hijo para operacion de matrices
		int operacion, k;
		if(pipe(desc_arch3)!=0)
			exit(1);
		if(pipe(desc_arch4)!=0)
			exit(1);
		if((id_proc1=fork())==0){
			//Suma de matrices
			read(desc_arch3[0], m1, sizeof(m1));
			read(desc_arch4[0], m2, sizeof(m2));
			for(i=0; i<TAM; i++){
				for(j=0; j<TAM; j++){
					matriz[i][j] = m2[i][j]+m1[i][j];
				}
			}
			/*printf("Suma de Matrices:\n");
			for(i=0; i<TAM; i++){
				for(j=0; j<TAM; j++)
					printf("%d ", matriz[i][j]);
				printf("\n");
			}*/

			write(rsum[1], matriz, sizeof(matriz)+1);
			exit(0);

		}
		else{	//padre que multiplica matrices
			read(desc_arch1[0], m1, sizeof(m1));
			read(desc_arch2[0], m2, sizeof(m2));

			//Multiplicacion
			for(i=0; i<TAM; i++){
				for(j=0; j<TAM; j++){
					operacion = 0;
					for(k=0; k<TAM; k++){
						operacion = operacion + (m1[i][k]*m2[k][j]);
						matriz[i][j] = operacion;
					}
				}
			}
			
			write(desc_arch3[1], m1, sizeof(m1)+1);
			write(desc_arch4[1], m2, sizeof(m2)+1);
			int r[TAM][TAM];
			
			write(rmul[1], matriz, sizeof(matriz));
			
		}

		exit(0);
		
	}
	//padre
	else{
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
		printf("MAtriz 2:\n");
		for(i=0; i<TAM; i++){
			for(j=0; j<TAM; j++)
				printf("%d ", m2[i][j]);
			printf("\n");
		}

		write(desc_arch1[1], m1, sizeof(m1)+1);
		write(desc_arch2[1], m2, sizeof(m2)+1);

		
		read(rsum[0], matriz, sizeof(matriz));
		printf("*************\n");

		printf("Suma de Matrices:\n");
			for(i=0; i<TAM; i++){
				for(j=0; j<TAM; j++)
					printf("%d ", matriz[i][j]);
			printf("\n");
		}
		inversa(matriz, "suma.txt");
		
		read(rmul[0], matriz, sizeof(matriz));
		printf("*************\n");

		printf("Multiplicacion de Matrices:\n");
			for(i=0; i<TAM; i++){
				for(j=0; j<TAM; j++)
					printf("%d ", matriz[i][j]);
			printf("\n");
		}
		inversa(matriz, "multiplicacion.txt");
			
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
