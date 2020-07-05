#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
DWORD WINAPI funcionHiloTerminal(LPVOID lpParam);
DWORD WINAPI funcionHiloPadre(LPVOID lpParam);
DWORD idHilo;

int main(void){
	HANDLE manHilo;
	int i=0, j=0, k=0;
	system("cls");
	if (manHilo= CreateThread(NULL, 0, funcionHiloPadre, NULL, 0, &idHilo)){
		printf("Hilo padre: ");
		WaitForSingleObject(manHilo, INFINITE);
		printf("\n");
		for (i=0; i<15; i++){
			manHilo= CreateThread(NULL, 0, funcionHiloPadre, NULL, 0, &idHilo);
			printf("Hilo %d de 15: ", i+1);
			WaitForSingleObject(manHilo, INFINITE);
			printf("\n");
			for (j=0; j<10; j++){
				manHilo= CreateThread(NULL, 0, funcionHiloPadre, NULL, 0, &idHilo);
				printf("Hilo %d de 10: ", j+1);
				WaitForSingleObject(manHilo, INFINITE);
				for(k=0; k<5; k++){
					manHilo= CreateThread(NULL, 0, funcionHiloTerminal, NULL, 0, &idHilo);
					printf("Hilo %d de 5: ", k+1);
					WaitForSingleObject(manHilo, INFINITE);
				}
			}
			printf("\n");
		}
	}
	CloseHandle(manHilo);
	return 0;
}

DWORD WINAPI funcionHiloTerminal(LPVOID lpParam){
	printf("Practica 5\n");
	return 0;
}

DWORD WINAPI funcionHiloPadre(LPVOID lpParam){
	printf("Identificador: %lu\n", idHilo);
	return 0;
}