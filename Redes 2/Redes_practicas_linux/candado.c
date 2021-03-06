#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void *f();
pthread_mutex_t m1 = PTHREAD_MUTEX_INITIALIZER; //Controla y libera el hilo
int cont = 0;
main(){
    int r1, r2;
    pthread_t t1, t2;
    if(r1 = pthread_create(&t1, NULL, f, NULL)){
        printf("Error al crear el hilo 1\n");
        exit(-1);
    }
    if(r2 = pthread_create(&t2, NULL, f, NULL)){
        printf("Error al crear el hilo 2\n");
        exit(-1);
    }
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    exit(0);
}

void *f(){
    pthread_mutex_lock(&m1); //Se crea un candado
    cont++;
    printf("El valor del candado es %d\n", cont);
    pthread_mutex_unlock(&m1); //Libera el candado
}