#include <semaphore.h>
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>

#define HILOS 20
sem_t okComprarLeche;
int LecheDisponible;

void *comprador(void *arg){
    int local;
    sem_wait(&okComprarLeche);
    if(LecheDisponible < 5){
        //Compraremos Leche
        local = LecheDisponible;
        local++;
        sleep(1);
        LecheDisponible = local;
        //++LecheDisponible;
    }
    sem_post(&okComprarLeche);
}

int main(int argc, char **argv){
    pthread_t hilos[HILOS];
    int i;
    LecheDisponible = 0;
    //Iniciamos el semaforo
    if(sem_init(&okComprarLeche, 0, 5)){
        printf("No se pudo inicializar el semaforo\n");
        return -1;
    }
    for(i = 0; i < HILOS; i++){
        if(pthread_create(&hilos[i], NULL, &comprador, NULL)){
            printf("Error al crear el hilo[%d]\n", i);
            return -1;
        }
    }
    for(i = 0; i <HILOS; i++)
        pthread_join(hilos[i], NULL);
    sem_destroy(&okComprarLeche);
    //Nos aseguramos de no tener demasiada leche
    printf("Total de leche: %d\n", LecheDisponible);
    return 0;
}
