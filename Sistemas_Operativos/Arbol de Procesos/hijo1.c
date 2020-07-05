#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>
int main(){
	char mensaje[20];
	DWORD leidos;
	HANDLE hStdln= GetStdHandle(STD_INPUT_HANDLE);
	SECURITY_ATTRIBUTES pipeSeg= {sizeof(SECURITY_ATTRIBUTES), NULL, TRUE};
	ReadFile(hStdln, mensaje, sizeof(mensaje), &leidos, NULL);
	printf("Mensaje recibido del proceso padre: %s\n", mensaje);
	CloseHandle(hStdln);
	return 0;
}