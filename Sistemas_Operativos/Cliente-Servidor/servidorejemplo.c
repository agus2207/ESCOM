#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#define TAM_MEM 27
int main (void){
	HANDLE hArchMapeo;
	char *idMemCompartida= "MemoriaCompartida";
	char *apDatos, *apTrabajo, c;
	if ((hArchMapeo= CreateFileMapping (INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, TAM_MEM, idMemCompartida))== NULL){
		printf("No se mapeo la memoria compartida: (%i)\n", GetLastError());
		exit (-1);
	}
	if((apDatos= (char*)MapViewOfFile(hArchMapeo, FILE_MAP_ALL_ACCESS, 0, 0, TAM_MEM))==NULL){
		printf("No se creo la memoria compartida: (%i)\n", GetLastError());
		CloseHandle(hArchMapeo);
		exit (-1);
	} //valor de retorno es la dirección de inicio de la vista mapeada.
	apTrabajo= apDatos;
	for (c= 'a'; c <='z'; c++)
		*apTrabajo++= c;
		*apTrabajo= '\0';
	while (*apDatos!= '*')
		sleep(1);
	UnmapViewOfFile(apDatos);
	CloseHandle(hArchMapeo);
	exit (0);
}