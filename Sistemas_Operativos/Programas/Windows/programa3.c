#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>
#include <dirent.h>
#include <sys/types.h>  
#include <sys/stat.h>  
#define MAX 100

DWORD WINAPI funcionCopiaArch (LPVOID lpParam);
DWORD WINAPI funcionCopiaDir (LPVOID lpParam);
DWORD WINAPI funcionAnaliza(LPVOID lpParam);

typedef struct datos{
	char rutaorigen[MAX];
	char rutadestino[MAX];
	char nombre[MAX];
	HANDLE hFilecpy;
}datos;

int main (void){
	datos dat;
	HANDLE manHilo;
	DWORD idHilo;
	printf("\t\t\t\tCopiador de archivos\n\n");
	printf("Ingresa la direcci%cn de la carpeta: ", 162);
	gets(dat.rutaorigen);
	printf("\n");
	printf("Ingresa la direcci%cn a donde se copiaran los archivos: ", 162);
	gets(dat.rutadestino);
	manHilo=CreateThread(NULL, 0, funcionAnaliza, &dat, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	return 0;
}

DWORD WINAPI funcionAnaliza(LPVOID lpParam){
	datos *dat= (datos*)lpParam;
	DIR *dirp;
 	struct dirent *direntp;
	struct stat buf;  
	DWORD idHilo;
	HANDLE manHilo;
	HANDLE hFile;
	LPCTSTR lpFileName;
  	DWORD dwDesiredAccess= GENERIC_READ;
 	DWORD dwShareMode= 0;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_EXISTING;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	
	dirp= opendir(dat->rutaorigen);
	if(dirp==NULL){
		printf("Ha ocurrido un error\n");
		return -1;
	}
	printf("Archivos existentes en la ruta: %s\n", dat->rutaorigen);
 	while ((direntp = readdir(dirp)) != NULL) {
		printf("%s\n", direntp->d_name);
		lpFileName="";
		lpFileName= direntp->d_name;
		SetCurrentDirectory(dat->rutaorigen);
		hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
		if (hFile == INVALID_HANDLE_VALUE){
			if (strcmp(lpFileName, ".")==0 || strcmp(lpFileName, "..")==0){
			}else{
				strcpy(dat->nombre,direntp->d_name);
				dat->hFilecpy=hFile;
				manHilo=CreateThread(NULL, 0, funcionCopiaDir, dat, 0, &idHilo);
				WaitForSingleObject(manHilo, INFINITE);
				CloseHandle(manHilo);
			}
		}else{
			strcpy(dat->nombre,direntp->d_name);
			dat->hFilecpy=hFile;
			manHilo=CreateThread(NULL, 0, funcionCopiaArch, dat, 0, &idHilo);
			WaitForSingleObject(manHilo, INFINITE);
			CloseHandle(manHilo);
		}
	}

}

DWORD WINAPI funcionCopiaArch (LPVOID lpParam){
	datos *dat= (datos*)lpParam;
	DIR *dirp;
	LPCTSTR lpFileName= dat->nombre;
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
	DWORD bytesWritten=0;
	char lpBuffer[150]="";
    DWORD nNumberOfBytesToRead= 150;
    DWORD lpNumberOfBytesRead=0;
    LPOVERLAPPED lpOverlapped= NULL;
	ReadFile(dat->hFilecpy, lpBuffer, nNumberOfBytesToRead, &lpNumberOfBytesRead, lpOverlapped);
  	if(ReadFile==NULL){
  		printf("Ha ocurrido un error\n");
  		return -1;
	  }
	dirp= opendir(dat->rutadestino);
	if(dirp==NULL){
		printf("Ha ocurrido un error :(\n");
		printf("%s\n", dat->rutadestino);
		return -1;
	}
	SetCurrentDirectory(dat->rutadestino);
	if(dat->hFilecpy= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		printf("El fichero %s se ha creado correctamente\n", dat->nombre);
		WriteFile(dat->hFilecpy, lpBuffer, sizeof(lpBuffer), &bytesWritten, NULL);
	}
	return 0;
}


DWORD WINAPI funcionCopiaDir (LPVOID lpParam){
	datos *dat= (datos*)lpParam;
	datos nuevastruct;
	HANDLE manHilo;
	DWORD idHilo;
	int num;
	LPCTSTR lpFileName;
	DWORD   nBufferLength= 256;
	char lpBuffer[MAX];
	LPTSTR  *lpFilePart;
	LPCTSTR lpPathName= dat->nombre;
	LPSECURITY_ATTRIBUTES lpSecurityAttributes=NULL;
	lpFileName=dat->nombre;
	num= GetFullPathName(lpFileName, nBufferLength, lpBuffer, lpFilePart);
	SetCurrentDirectory(dat->rutadestino);
	strcpy(nuevastruct.rutaorigen, lpBuffer);
	strcpy(nuevastruct.rutadestino,dat->rutadestino);
	if (CreateDirectory (lpPathName,lpSecurityAttributes)){
		printf("El directorio se ha creado con exito\n");
	}
	manHilo=CreateThread(NULL, 0, funcionAnaliza, &nuevastruct, 0, &idHilo);
	WaitForSingleObject(manHilo, INFINITE);
	CloseHandle(manHilo);
	
}