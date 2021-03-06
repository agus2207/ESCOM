#include<sys/types.h>
#include<stdlib.h>
#include<stdio.h>
 
int main(){
    int i = 0;
    switch(fork()){
        case -1: 
            perror("Error al crear proceso");
            exit(-1);
            break;
        case 0:     //Codigo del hijo
            while(i < 10){
                printf("\t\tSoy el hijo: %d\n", i++);
                sleep(1);
            }
            break;
        default:    //Codigo del padre
            while(i < 10){
                printf("Soy el padre: %d\n", i++);
                sleep(1);
            }
    }
    exit(0);
}