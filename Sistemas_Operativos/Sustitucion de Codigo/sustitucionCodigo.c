#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
	int i;
	pid_t hijo, nieto;
	char *argv[4];
	argv[0]="expresion";
	argv[1]="cambiaPermisoA";
	argv[2]="inversa";
	argv[3]=NULL;
	if((hijo=fork())==0){
		printf("\tSoy el proceso hijo 1\n");
		for (i=0; i<3; i++){
			if((nieto=fork())==0){
				printf("\t\tSoy el proceso nieto %d\n", i+1);
				if((execv(argv[i], argv)) == -1)
					printf("No se pudo crear el programa %s\n", argv[i]);
				exit(0);
			}
			wait(&nieto);
		}
		exit(0);
	}
	else{
		wait(&hijo);
		printf("Soy el Proceso Padre\n");
		exit(0);
	}
}
