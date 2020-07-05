#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#define TAM_MEM 27
int main (void){
	HANDLE hArchMapeo;
	char *idMemCompartida= "MemoriaCompartida";
	char *apDatos, *apTrabajo, c;
	if ((hArchMapeo= OpenFileMapping (FILE_MAP_ALL_ACCESS, FALSE, idMemCompartida))== NULL){
		printf("No se abrio archivo de mapeo de la memoria compartida: (%i)\n", GetLastError());
		exit (-1);
	}
	if((apDatos= (char*)MapViewOfFile(hArchMapeo, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM))==NULL){
		printf("No se accedio a la memoria compartida: (%i)\n", GetLastError());
		CloseHandle(hArchMapeo);
		exit (-1);
	}
	for (apTrabajo= apDatos; *apTrabajo!='\0'; apTrabajo++)
		putchar (*apTrabajo);
		putchar('\n');
		*apDatos= '*';
		UnmapViewOfFile(apDatos);
		CloseHandle(hArchMapeo);
		exit (0);
}