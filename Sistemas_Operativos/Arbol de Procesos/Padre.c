#include <windows.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include <math.h>

struct matrices
{
    int matriz1[10][10];
    int matriz2[10][10];
    int matriz3[10][10];
    char res[14];
};

void imprimirMatriz(struct matrices *m);
void crearArchivo( char* resultado , char* archivo);
double Determinante (double m1[10][10],int n);
void matrizCofactores (double m1[10][10], int n, double determinante);
void matrizCofactores2 (double m1[10][10], int n, double determinante);

HANDLE hLecturaPipeIn = NULL, hEscrituraPipeIn = NULL;
HANDLE hLecturaPipeOut = NULL, hEscrituraPipeOut = NULL;

int main (int argc, char *argv[]){
	srand (time(NULL));
	int h, i, j;
	double determinante1;
	double determinante2;
	double inv1[10][10]; 
	double inv2[10][10];
	system("cls");
    printf("\tMatrices del padre\n\n");
   struct matrices mult;
   struct matrices *res1, *res2; 
   res1 = (struct matrices*)malloc(sizeof(struct matrices)); 
   res2 = (struct matrices*)malloc(sizeof(struct matrices));
   DWORD escritos, leidos; 
   for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			for (h=0; h<10; h++){
				mult.matriz1[i][j]=rand()%9;
			}
			for (h=0; h<10; h++){
				mult.matriz2[i][j]=rand()%9;
			}
		}
	}
	printf("\n\t\tMatriz 1:\n\n");
    for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ",mult.matriz1[i][j]);
		}
		printf("\n");
	}
	printf("\n\n\n\t\tMatriz 2:\n\n");
    for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ",mult.matriz2[i][j]);
		}
		printf("\n");
	}
   
   
   SECURITY_ATTRIBUTES pipeSeg = {sizeof(SECURITY_ATTRIBUTES), NULL, TRUE}; 
   CreatePipe (&hLecturaPipeOut, &hEscrituraPipeOut, &pipeSeg, 0);
   CreatePipe (&hLecturaPipeIn, &hEscrituraPipeIn, &pipeSeg, 0); 
   WriteFile(hEscrituraPipeIn, &mult, sizeof(struct matrices), &escritos, NULL);
   PROCESS_INFORMATION piHijo;
   STARTUPINFO siHijo;
   GetStartupInfo (&siHijo);
   siHijo.hStdError  = GetStdHandle (STD_ERROR_HANDLE);
   siHijo.hStdOutput = hEscrituraPipeOut;   
   siHijo.hStdInput  = hLecturaPipeIn;  
   siHijo.dwFlags = STARTF_USESTDHANDLES;
   CreateProcess (NULL,"Hijo.exe",NULL,NULL,TRUE,0,NULL,NULL,&siHijo,&piHijo);   
   WaitForSingleObject (piHijo.hProcess, INFINITE); 
   CloseHandle(piHijo.hThread);
   CloseHandle(piHijo.hProcess); 
   CloseHandle(hEscrituraPipeIn);  
   ReadFile(hLecturaPipeOut, res1, sizeof(struct matrices), &leidos, NULL);
   ReadFile(hLecturaPipeOut, res2, sizeof(struct matrices), &leidos, NULL); 
   CloseHandle(hLecturaPipeOut); 
   CloseHandle(hEscrituraPipeOut); 
   CloseHandle(hLecturaPipeIn); 
   printf("\n\t\tMultiplicacion\n\n");
   for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", res1->matriz3[i][j]);
		}
		printf("\n");
	}
   
   printf("\n\n\t\tSuma\n\n");
   for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", res2->matriz3[i][j]);
		}
		printf("\n");
	}
   for(i= 0; i<10; i++ ){
	   for(j= 0; j<10; j++ ){
           inv1[i][j]= res1->matriz3[i][j];
           inv2[i][j]= res2->matriz3[i][j];
       }
   }
   determinante1= Determinante(inv1, 10);
    if (determinante1!= 0){
		matrizCofactores(inv1, 10, determinante1);
    }else{
        printf("\nLa matriz 1 no es invertible\n");
    }
    determinante2= Determinante(inv2, 10);
    if (determinante2!= 0){
        matrizCofactores2(inv2, 10, determinante2);
    }else{
        printf("\nLa matriz 2 no es invertible\n");
    }
   return 0;
}


void crearArchivo( char* resultado , char* archivo)
{
    HANDLE hFile;       
    DWORD dwBytesToWrite;   
    DWORD dwBytesWritten = 0; 
    bool bErrorFlag = FALSE; 
    dwBytesToWrite = (DWORD)strlen(resultado);
    hFile = CreateFile(archivo,GENERIC_WRITE,0,NULL,CREATE_ALWAYS,FILE_ATTRIBUTE_NORMAL,NULL); 
    bErrorFlag = WriteFile(hFile,resultado,dwBytesToWrite,&dwBytesWritten,NULL);
    if(!bErrorFlag){
        printf("\tError al crear el archivo\n\n");
	}
    CloseHandle(hFile); 
}

double Determinante (double m1[10][10],int n)
{
  double s= 1, det= 0;
  int i, j, k, m, x;
  double m2[10][10];
  if (n == 1)
    return m1[0][0];
  else{
    for (k= 0;k<n; k++)
    {
      m= 0;
      x= 0;
      for (i= 0; i<n; i++)
        for (j= 0; j<n; j++){
          m2[i][j]= 0;
          if (i!= 0 && j!= k){
            m2[m][x] = m1[i][j];
            if (x<(n-2))
              x++;
            else
            {
              x= 0;
              m++;
            }
          }
        }
      det= det+s*(m1[0][k]*Determinante(m2, n-1));
      s*= -1;
  }
  return det;
    }
}

void matrizCofactores (double m1[10][10], int n, double determinante)
{
  char resultado[700];
  char buffer[8]={};
  int q, m, i, j, k, l;
  double m2[10][10], matrizFactores[10][10], aux[10][10], inversa[10][10];
  for (l= 0; l<n; l++){
    for (k=0; k<n; k++){
      m= 0; q= 0;
      for (i= 0; i<n; i++){
        for (j=0; j<n; j++){
          if (i!= l && j!= k){
            m2[m][q]= m1[i][j];
            if (q < (n-2)){
              q++;
            }else{
                q= 0; 
				m++;
            }
          }
        }
      }
      matrizFactores[l][k] = (pow(-1, l+k)*Determinante (m2, n-1));
    }
  }
  for (i= 0; i<10; i++){
    for (j= 0; j<10; j++){
      aux[i][j]= matrizFactores[j][i];
    }
  }
  for (i=0; i<10; i++){
    for (j=0; j<10; j++){
      inversa[i][j] = aux[i][j]/determinante;
    }
  }
  for (i= 0; i<10; i++){
    for (j= 0; j<10; j++){
      sprintf(buffer,"%.3lf\t",inversa[i][j]);
      strcat(resultado,buffer);
      memset(buffer,0,8);
    }
    strcat(resultado,"\r\n");
  }
  crearArchivo(resultado,"Suminver.txt");
}

void matrizCofactores2 (double m1[10][10], int n, double determinante)
{
  char resultado[700];
  char buffer[8]={};
  int q, m, i, j, k, l;
  double m2[10][10], matrizFactores[10][10], aux[10][10], inversa[10][10];
  for (l= 0; l<n; l++){
    for (k= 0; k<n; k++){
      m= 0; 
	  q= 0;
      for (i= 0; i<n; i++){
        for (j= 0; j<n; j++){
          if (i!= l && j!= k){
            m2[m][q] = m1[i][j];
            if (q<(n-2)){
              q++;
            }else{
                q= 0;
				m++;
            }
          }
        }
      }
      matrizFactores[l][k] = (pow(-1, l+k)*Determinante(m2, n-1));
    }
  }
  for (i= 0; i<10; i++){
    for (j= 0; j<10; j++){
      aux[i][j] = matrizFactores[j][i];
    }
  }
  for (i= 0; i<10; i++){
    for (j= 0; j<10; j++){
      inversa[i][j] = aux[i][j]/determinante;
    }
  }
  for (i= 0; i<10; i++){
    for (j= 0; j<10; j++){
      sprintf(buffer,"%.3lf\t",inversa[i][j]);
      strcat(resultado,buffer);
      memset(buffer, 0, 8);
    }
      strcat(resultado,"\r\n");
  }
  crearArchivo(resultado,"Multinver.txt");
}
