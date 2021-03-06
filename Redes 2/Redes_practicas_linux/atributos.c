#include <pthread.h>
#include <limits.h>
#include "errores.h"
#include <stdio.h>
#include <stdlib.h>

void codigo_del_hilo(void* arg){
    pthread_attr_t *atributos = arg;
    int detachstate;
    int tam_pila;
    int error;
    error = pthread_attr_getdetachstate(atributos, &detachstate);
    if(error)
        error_fatal(error, "getdetachstate");
    else if(detachstate == PTHREAD_CREATE_DETACHED)
        printf("Hilo separado\n");
    else    
        printf("Hilo no separado\n");
    error = pthread_attr_getstacksize(atributos, &tam_pila);
    if(error)
        error_fatal(error, "getstacksize");
    else
        printf("Hilo. Tamaño de la pila, %d bytes = %d X %d\n", tam_pila, tam_pila/PTHREAD_STACK_MIN, PTHREAD_STACK_MIN);
    return NULL;
}

int main(){
    pthread_t hilo;
    pthread_attr_t atributos;
    size_t tam_pila;
    int error;
    //inicializacion de los atributos
    error = pthread_attr_init(&atributos);
    if(error)   
        error_fatal(error, "attr_init");
    error = pthread_attr_setdetachstate(&atributos, PTHREAD_CREATE_DETACHED);
    if(error)
        error_fatal(error, "setdetachstate");
    //Manipulamos tamaño de la pila
    error = pthread_attr_getstacksize(&atributos, &tam_pila);
    if(error)
        error_fatal(error, "getstacksize");
    else{
        printf("Tamaño pila X defecto: %d\n", tam_pila);
        printf("Tamaño minimo pila: %d\n", PTHREAD_STACK_MIN);
    }
    error = pthread_attr_setstacksize(&atributos, 3*PTHREAD_STACK_MIN);
    if(error)
        error_fatal(error, "setstacksize");
    //Creamos el hilo con los atributos
    error = pthread_create(&hilo, &atributos, codigo_del_hilo, &atributos);
    if(error)
        error_fatal(error, "create");
    printf("Fin del hilo principal\n");
    pthread_exit(NULL);
}