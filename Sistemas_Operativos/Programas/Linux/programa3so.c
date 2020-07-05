#include <stdio.h>  
#include <stdlib.h>
#include <dirent.h>
#include <Windows.h>
 #include <sys/types.h>  
#include <sys/stat.h>  
#define MAX 100

int main (int argc, char **argv[]){
	char *direccion= (char*)malloc(sizeof(char)*MAX);
	char *direccion2= (char*)malloc(sizeof(char)*MAX);
	char *archex= (char*)malloc(sizeof(char)*MAX);
	char *archnue= (char*)malloc(sizeof(char)*MAX);
	DIR *dirp;
 	struct dirent *direntp;
	struct stat buf;  
	
	HANDLE hFile;
	LPCTSTR lpFileName;
  	DWORD dwDesiredAccess= GENERIC_READ;
 	DWORD dwShareMode= 0;
 	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
  	DWORD dwCreationDisposition= OPEN_EXISTING;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;
  	
  	char lpBuffer[150]="";
    DWORD nNumberOfBytesToRead= 150;
    DWORD lpNumberOfBytesRead=0;
    LPOVERLAPPED lpOverlapped= NULL;
  	
	printf("\t\t\t\tCopiador de archivos\n\n");
	printf("Ingresa la direcci%cn de la carpeta: ", 162);
	gets(direccion);
	printf("\n");
	dirp= opendir(direccion);
	if(dirp==NULL){
		printf("Ha ocurrido un error\n");
		return -1;
	}
 	while ((direntp = readdir(dirp)) != NULL) {
		printf("%s\n", direntp->d_name);
 	}
 	closedir(dirp);
	SetCurrentDirectory(direccion);
	printf("\nIngresa el nombre del archivo que deseas leer\n");
	gets(archex);
	lpFileName= archex;
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
	printf("%s\n", lpBuffer);
	printf("\nIngresa el nuevo destino donde se copiar%c: %s\n", 160, archex);
	gets(direccion2);
	dirp= opendir(direccion2);
	if(dirp==NULL){
		printf("Ha ocurrido un error\n");
		return -1;
	}
	SetCurrentDirectory(direccion2);
	printf("Ingresa el nombre al nuevo archivo: ");
	gets(archnue);
	lpFileName= archnue;
	dwDesiredAccess= FILE_APPEND_DATA;
	dwShareMode= FILE_SHARE_READ;
	lpSecurityAttributes= NULL;
	dwCreationDisposition= OPEN_ALWAYS;
	dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL; 
	hTemplateFile= NULL;		
	DWORD bytesWritten=0;
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		printf("El fichero %s se ha creado correctamente\n", archnue);
		WriteFile(hFile, lpBuffer, sizeof(lpBuffer), &bytesWritten, NULL);
	}
}
