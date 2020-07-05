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
  	char lpBuffer[200]="";
    DWORD nNumberOfBytesToRead= 200;
    DWORD lpNumberOfBytesRead=0;
    LPOVERLAPPED lpOverlapped= NULL;
	lpFileName= "Suma.txt";
	int mat1[10][10];
	int mat2[10][10];
	int i=0, j=0, k=0;
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
	printf("\n\tSuma de matrices\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", mat1[i][j]);
		}
		printf("\n");
	}
	CloseHandle(hFile);
	/////////////////////////////////////////////Imprime la resta 
	char lpBuffer2[200]="";
  	dwDesiredAccess= GENERIC_READ;
 	dwShareMode= 0;
 	lpSecurityAttributes= NULL;
  	dwCreationDisposition= OPEN_EXISTING;
  	dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	hTemplateFile= NULL;
    nNumberOfBytesToRead= 200;
    lpNumberOfBytesRead=0;
    lpOverlapped= NULL;
	lpFileName= "Resta.txt";
	i=0, j=0, k=0;
	hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
	if (hFile == INVALID_HANDLE_VALUE){
     	printf("El archivo no se puede abrir\n");
     	return -1;
  	}	
  	ReadFile(hFile, lpBuffer2, nNumberOfBytesToRead, &lpNumberOfBytesRead, lpOverlapped);
  	if(ReadFile==NULL){
  		printf("Ha ocurrido un error\n");
  		return -1;
	  }
	  
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat1[i][j]= (int)(lpBuffer2[k]-48);
			k++;
		}
	}

	printf("\n\tResta de matrices\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", mat1[i][j]);
		}
		printf("\n");
	}
	CloseHandle(hFile);
	///////////////////////////////////////////////////////////Imprime la multiplicacion
	char lpBuffer3[200]="";
  	dwDesiredAccess= GENERIC_READ;
 	dwShareMode= 0;
 	lpSecurityAttributes= NULL;
  	dwCreationDisposition= OPEN_EXISTING;
  	dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	hTemplateFile= NULL;
    nNumberOfBytesToRead= 200;
    lpNumberOfBytesRead=0;
    lpOverlapped= NULL;
	lpFileName= "Multiplicacion.txt";
	i=0, j=0, k=0;
	hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
	if (hFile == INVALID_HANDLE_VALUE){
     	printf("El archivo no se puede abrir\n");
     	return -1;
  	}	
  	ReadFile(hFile, lpBuffer3, nNumberOfBytesToRead, &lpNumberOfBytesRead, lpOverlapped);
  	if(ReadFile==NULL){
  		printf("Ha ocurrido un error\n");
  		return -1;
	  }
	  
	  
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat1[i][j]= (int)(lpBuffer3[k]-48);
			k++;
		}
	}
	
	for(i=0; i<10; i++){
		for (j=0; j<10; j++){
			if(mat1[i][j]<0){
				mat1[i][j]= 256+mat1[i][j];
			}
		}
	} 
	
	printf("\n\tMultiplicacion de matrices\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", mat1[i][j]);
		}
		printf("\n");
	}
	CloseHandle(hFile);
	///////////////////////////////////////////////Imprime la transpuesta
	
	char lpBuffer4[200]="";
	char lpBuffer5[200]="";
	int mat3[10][10];
	int mat4[10][10];
  	dwDesiredAccess= GENERIC_READ;
 	dwShareMode= 0;
 	lpSecurityAttributes= NULL;
  	dwCreationDisposition= OPEN_EXISTING;
  	dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	hTemplateFile= NULL;
    nNumberOfBytesToRead= 200;
    lpNumberOfBytesRead=0;
    lpOverlapped= NULL;
	lpFileName= "Transpuesta.txt";
	i=0, j=0, k=0;
	hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
	if (hFile == INVALID_HANDLE_VALUE){
     	printf("El archivo no se puede abrir\n");
     	return -1;
  	}	
  	ReadFile(hFile, lpBuffer4, nNumberOfBytesToRead, &lpNumberOfBytesRead, lpOverlapped);
  	if(ReadFile==NULL){
  		printf("Ha ocurrido un error\n");
  		return -1;
	  }
	  
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat1[i][j]= (int)(lpBuffer4[k]-48);
			k++;
		}
	}
	printf("\n\tTranspuesta de matrices\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", mat1[i][j]);
		}
		printf("\n");
	}
	///////////////////////////////
	
	printf("\n");
	k=100;//////////////////////////////////////////////////Cambiar el valor de k 
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			mat2[i][j]= (int)(lpBuffer4[k]-48);
			k++;
		}
	}
	
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", mat2[i][j]);
		}
		printf("\n");
	}
	CloseHandle(hFile);
	exit (0);
}