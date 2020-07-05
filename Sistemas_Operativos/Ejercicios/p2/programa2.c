#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
DWORD WINAPI funcionSuma(LPVOID lpParam);
DWORD WINAPI funcionResta(LPVOID lpParam);
DWORD WINAPI funcionMulti(LPVOID lpParam);
DWORD WINAPI funcionTrans(LPVOID lpParam);
DWORD WINAPI funcionLee(LPVOID lpParam);

typedef struct matriz{
	int mat1[10][10];
	int mat2[10][10];
}matrices;

int main (void){
	srand (time(NULL));
	DWORD idHilo;
	HANDLE manHilo;
	matrices matriz;
	int i=0, j=0;
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matriz.mat1[i][j]=rand()%9;
		}
	}
	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matriz.mat2[i][j]=rand()%9;
		}
	}
	system("cls");
	printf("\n\tMatriz numero 1:\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", matriz.mat1[i][j]);
		}
		printf("\n");
	}
	printf("\n\n\tMatriz numero 2:\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%d ", matriz.mat2[i][j]);
		}
		printf("\n");
	}
	
	manHilo=CreateThread(NULL, 0, funcionSuma, &matriz, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
	manHilo=CreateThread(NULL, 0, funcionResta, &matriz, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
	manHilo=CreateThread(NULL, 0, funcionMulti, &matriz, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
	manHilo=CreateThread(NULL, 0, funcionTrans, &matriz, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
	manHilo=CreateThread(NULL, 0, funcionLee, &matriz, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
	return 0;
}


DWORD WINAPI funcionSuma(LPVOID lpParam){
	matrices *matriz= (matrices*)lpParam;
	int i=0, j=0;
	int matr[10][10];
	char matesc[10][10];
	HANDLE hFile;
	LPCTSTR lpFileName="Suma.txt";
	DWORD bytesWritten=0;
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matr[i][j]= matriz->mat1[i][j]+matriz->mat2[i][j];
		}
	}
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc[i][j]= (char)(matr[i][j]+48);
		}
	}
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, matesc, sizeof(matesc), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
}


DWORD WINAPI funcionResta(LPVOID lpParam){
	matrices *matriz= (matrices*)lpParam;
	int i=0, j=0;
	int matr[10][10];
	char matesc[10][10];
	HANDLE hFile;
	LPCTSTR lpFileName="Resta.txt";
	DWORD bytesWritten=0;
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matr[i][j]= matriz->mat1[i][j]-matriz->mat2[i][j];
		}
	}	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc[i][j]= (char)(matr[i][j]+48);
		}
	}
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, matesc, sizeof(matesc), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
}


DWORD WINAPI funcionMulti(LPVOID lpParam){
	matrices *matriz= (matrices*)lpParam;
	int i=0, j=0, h=0, aux=0;
	int matr[10][10];
	char matesc[10][10];
	HANDLE hFile;
	LPCTSTR lpFileName="Multiplicacion.txt";
	DWORD bytesWritten=0;
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	for (h=0; h<10; h++){
		for (i=0; i<10; i++){
			for (j=0; j<10; j++){
				aux= aux+((matriz->mat1[h][j])*(matriz->mat2[j][i]));
				matr[h][i]= aux;
			}
			aux=0;
		}
	}	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc[i][j]= (char)(matr[i][j]+48);
		}
	}
	
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, matesc, sizeof(matesc), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
}


DWORD WINAPI funcionTrans(LPVOID lpParam){
	matrices *matriz= (matrices*)lpParam;
	int i=0, j=0;
	int matr1[10][10];
	int matr2[10][10];
	char matesc1[10][10];
	char matesc2[10][10];
	HANDLE hFile;
	LPCTSTR lpFileName="Transpuesta.txt";
	DWORD bytesWritten=0;
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matr1[j][i]= matriz->mat1[i][j];
		}
	}
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matr2[j][i]= matriz->mat2[i][j];
		}
	}
	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc1[i][j]= (char)(matr1[i][j]+48);
		}
	}
	
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			matesc2[i][j]= (char)(matr2[i][j]+48);
		}
	}
	
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, matesc1, sizeof(matesc1), &bytesWritten, NULL);
		WriteFile(hFile, matesc2, sizeof(matesc2), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
}


DWORD WINAPI funcionLee(LPVOID lpParam){
	matrices *matriz= (matrices*)lpParam;
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
}
