#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
int main (int argc, char *argv[]){
	int i=0, j=0;
	char mat1[10][10];
	char mat2[10][10];
	
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	ZeroMemory(&si, sizeof(si));  	/*Estructura de información inicial para Windows*/
	si.cb= sizeof(si);				/*Estructura de información del adm. de procesos*/
	ZeroMemory(&pi, sizeof(pi));
	
	DWORD bytesWritten=0;
	UINT uStyile= OF_READ;
	LPSECURITY_ATTRIBUTES lpSecurityAttributes= NULL;
	HANDLE hFile;
	LPCTSTR lpFileName= "Matriz.txt";
  	DWORD dwDesiredAccess= FILE_APPEND_DATA;
 	DWORD dwShareMode= FILE_SHARE_READ;
  	DWORD dwCreationDisposition= OPEN_ALWAYS;
  	DWORD dwFlagsAndAttributes= FILE_ATTRIBUTE_NORMAL;
  	HANDLE hTemplateFile= NULL;

	printf("Favor de llenar la primer matriz\n\n");
	for(i=0; i<10; i++){
		for (j=0; j<10; j++){
			fflush(stdin);
			printf("Posicion %d,%d: ", i, j);
			scanf("%c", &mat1[i][j]);
		}
	}
	
	printf("\nFavor de llenar la segunda matriz\n");
	for (i=0; i<10; i++){
		for (j=0; j<10; j++){
			fflush(stdin);
			printf("Posicion %d,%d: ", i, j);
			scanf("%c", &mat2[i][j]);
		}
	}
	
	system("cls");
	
	printf("\tMatriz numero 1\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%c ", mat1[i][j]);
		}
		printf("\n");
	}
	
	printf("\n\n\tMatriz numero 2\n\n");
	for (i=0; i<10; i++){
		printf("\t");
		for (j=0; j<10; j++){
			printf("%c ", mat2[i][j]);
		}
		printf("\n");
	}
	
	
	if(hFile= CreateFile(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile)){
		WriteFile(hFile, mat1, sizeof(mat1), &bytesWritten, NULL);
		WriteFile(hFile, mat2, sizeof(mat2), &bytesWritten, NULL);
	}else{
		printf("No se pudo generar el fichero\n");
	}
	CloseHandle(hFile);
	
	if (CreateProcess(NULL, argv[1], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		WaitForSingleObject(pi.hProcess, INFINITE);
	}

	if (CreateProcess(NULL, argv[2], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		WaitForSingleObject(pi.hProcess, INFINITE);
	}
	
	if (CreateProcess(NULL, argv[3], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		WaitForSingleObject(pi.hProcess, INFINITE);
	}

	if (CreateProcess(NULL, argv[4], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		WaitForSingleObject(pi.hProcess, INFINITE);
	}
	
	if (CreateProcess(NULL, argv[5], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi)){
		WaitForSingleObject(pi.hProcess, INFINITE);
	}
	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);
}