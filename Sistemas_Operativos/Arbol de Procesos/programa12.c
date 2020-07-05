#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
int main (void){
	printf("Soy un proceso hijo con Id: %lu\n", GetCurrentProcessId());
	exit (0);
}