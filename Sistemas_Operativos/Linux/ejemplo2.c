#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
int main(void)
{
	int id_proc;
	id_proc = fork();
	if(id_proc == 0){
		printf("Soy el proceso hijo\n");
	}		
	else{
		printf("Soy el proceso padre\n");
	}
	printf("Mensaje en ambos\n");
	exit(0);}