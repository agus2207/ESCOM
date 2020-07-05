#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

int main (int argc, char *argv[]){
	HANDLE hFile;
	LPCTSTR lpFileName;
  	DWORD dwDesiredAccess= GENERIC_READ;
 	DWORD dwShareMode= 0;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_EXISTING;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
  	char lpBuffer[400]="";
    DWORD nNumberOfBytesToRead= 400;
    DWORD lpNumberOfBytesRead=0;
    LPOVERLAPPED lpOverlapped= NULL;
	lpFileName= "Matriz.txt";
	int mat1[10][10];
	int mat2[10][10];
	int matr[10][10];
	char matesc[10][10];
	int i=0, j=0, k=0, l=0, h=0, aux=0;
	hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
	if (hFile == INVALID_HANDLE_VALUE){
     	printf("El archivo no se puede abrir\n");
     	return -1;
  	}	
  	ReadFile(hFile, lpBuffer, nNumberOfBytesToRead, &lpNumberOfBytesRead, lpOverlapped);
  	if(ReadFile==NULL){
  		printf("Ha ocurrido un error\n");
  		return -1;
	  }
	  
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat1[i][j]= (int)(lpBuffer[k]-48);
			k++;
		}
	}
	
	k=100; //////////////////////Cambiar el valor de k 
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat2[i][j]= (int)(lpBuffer[k]-48);
			k++;
		}
	}
	
	/*for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			printf("%d ", mat1[i][j]);
		}
		printf("\n");
	}
	
	printf("\n");
	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			printf("%d ", mat2[i][j]);
		}
		printf("\n");
	}*/
	
	for (h=0; h<10; h++){
		for (i=0; i<10; i++){
			for (j=0; j<10; j++){
				aux= aux+(mat1[h][j]*mat2[j][i]);
				matr[h][i]= aux;
			}
			aux=0;
		}
	}
	
	/*for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			printf("%d ", matr[i][j]);
		}
		printf("\n");
	}*/
	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc[i][j]= (char)(matr[i][j]+48);
		}
	}
	
	/*for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			printf("%c ", matesc[i][j]);
		}
		printf("\n");
	}*/
	
	DWORD bytesWritten=0;
	OF_READ;
	lpSecurityAttributes= NULL;
	lpFileName= "Multiplicacion.txt";
  	dwDesiredAccess= FILE_APPEND_DATA;
 	dwShareMode= FILE_SHARE_READ;
  	dwCreationDisposition= OPEN_ALWAYS;
  	dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	hTemplateFile= NULL;
	
	
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, matesc, sizeof(matesc), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
	
	exit (0);
}