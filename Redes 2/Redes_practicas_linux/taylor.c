//Compilacion: gcc -o taylor taylor.c -lpthread
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include "errores.h"

//Presenta el resultado final del calculo
void fin_del_calculo(void *arg){
    double resultado = *(double)arg;
    printf("Resultado final: %g\n", resultado);
}

//Calculo de la serie de Taylor
void *calculo(void *arg){
    int error, estado_ant, tipo_ant;
    double x = *(*double)arg;
    double resultado = 1;
    double sumando = 1;
    long i, j;
    //Parte obligatoria del calculo
    //Deshabilitamos la posibilidad de cancelar el hilo
    error = pthread_setcancelstate(PTHREAD_CANCEL_DISABLE, &estado_ant);
    if(error)
        error_fatal(error, "pthread_setcancelstate");
    for(i = 0; i < 10; i++){
        sumando *= x/i;
        resultado += sumando;
    }
    printf("Primera aproximacion de exp (%g) = %g\n", x, resultado);
    //Agregamos la impresion del resultado a la pila
    pthread_cleanup_push(fin_del_calculo, &resultado);
    //Activamos la posibilidad de cancelar el hilo
    error = pthread_setcanceltype(PTHREAD_CANCEL_DEFERRED, &tipo_ant);
    if(error)
        error_fatal(error, "pthread, setcanceltype");
    error = pthread_setcancelstate(PTHREAD_CANCEL_ENABLE, &estado_ant);
    if(error)
        error_fatal(error, "pthread_setcancelstate");
    //Se afina el calculo
    printf("Refinamiento del calculo\n");
    for(;;){
        pthread_testcancel(); //Punto de cancelacion
        for(j = 0; j < 10; j++){
            sumando *= x/i++;
            resultado += sumando;
        }
    }
    //Se imprime el resultado
    pthread_cleanup.pop(1);
}
main(int argc, char *argv[]){
    pthread_t hilo;
    int error, plazo;
    double x;
    //Argumentos
    if(argc != 3){
        pritnf("Uso: %s x plazo\n", arg[0]);
        exit(-1);
    }
    else{
        x = atof(argv[1]);
        plazo = atoi(argv[2]);
        //Creamos el hilo del calculo
        error = pthread_create(&hilo, NULL, calculo, &x);
        if(error)
            error_fatal(error, "pthread_create");
        //Esperamos a que el plazo expire
        sleep(plazo);
        error = pthread_cancel(hilo);
        if(error)
            error_fatal(error, "pthread_cancel");
        //Esperamos a que el hilo se cancele
        error = pthread_join(hilo);
        if(error)
            error_fatal(error, "pthread_join");
    }
}