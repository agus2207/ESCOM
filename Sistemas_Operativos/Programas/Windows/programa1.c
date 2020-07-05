#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
int main (int argc, char *argv[]){
	int i=0, j=0;
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));  	/*Estructura de información inicial para Windows*/
	si.cb= sizeof(si);				/*Estructura de información del adm. de procesos*/
	ZeroMemory(&pi, sizeof(pi));
	if (argc!=2){
		perror("Faltan argumentos\n");
		return;
	}
	if (CreateProcess(NULL, argv[1], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		printf("Soy el padre\n");
		WaitForSingleObject(pi.hProcess, INFINITE);
		for (i=0; i<5; i++){
			if (CreateProcess(NULL, argv[1], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
				printf("\tProceso numero %d, ", i+1);
				WaitForSingleObject(pi.hProcess, INFINITE);
				for (j=0; j<3; j++){
					if (CreateProcess(NULL, argv[1], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
						printf("\t\tProceso numero %d, ", j+1);
						WaitForSingleObject(pi.hProcess, INFINITE);
					}else{
						break;
					}
				}
				exit;
			}
			printf("\n");
		}
	}
	
	
	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);
	
}