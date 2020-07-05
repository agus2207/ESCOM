#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <pthread.h>
#include <sys/syscall.h>

pid_t id;

void *hiloAbuelo(void *arg);
void *hiloPadre(void *arg);
void *hiloHijo(void *arg);

int main(void){
	int i;
	pid_t hijo;
	pthread_t  id_hiloAbuelo;
	if((hijo = fork())==0){
		printf("\tSoy el proceso Hijo mi ID es: %d\n", getpid());
		for(i=0; i<15; i++){
			pthread_create(&id_hiloAbuelo, NULL, (void*)hiloAbuelo, (void*)i);
			pthread_join(id_hiloAbuelo, NULL);
		}
		exit(0);
	}
	else{
		wait(&hijo);
		printf("Soy el proceso Padre mi ID es %d\n", getpid());
		exit(0);
	}
}

void *hiloAbuelo(void *arg){
	int i;
	pthread_t id_hiloPadre;
	printf("\t\tSoy el Hilo Abuelo %d mi ID es %ld\n", (arg+1), (long)syscall(SYS_gettid));

	for(i=0; i<10; i++){
			pthread_create(&id_hiloPadre, NULL, (void*)hiloPadre, (void*)i);
			pthread_join(id_hiloPadre, NULL);
		}
	return NULL;
}

void *hiloPadre(void *arg){
	int i;
	pthread_t id_hiloHijo;
	printf("\t\t\tSoy el Hilo Padre %d mi ID es %ld\n", (arg+1), (long) syscall(SYS_gettid));
	for(i=0; i<5; i++){
			pthread_create(&id_hiloHijo, NULL, (void*)hiloHijo, (void*)i);
			pthread_join(id_hiloHijo, NULL);
		}
	return NULL;
}

void *hiloHijo(void *arg){
	printf("\t\t\t\tPractica 5 Soy el Hilo Hijo %d mi ID es %ld\n", (arg+1), (long) syscall(SYS_gettid));	
}