#include<sys/types.h>
#include<stdlib.h>
#include<stdio.h>
#include<sys/wait.h>

#define NUM_PROCESOS 5
int I = 0;  //Variable global

void codigo_del_proceso(int id){
    int i;  //Variable local
    for(i = 0; i < 50; i++)
        printf("Proceso %d: i=%d, I=%d\n", id, i, I++);
    //El valor de id se almacena en los bits 8 al 15
    exit(id);
}

main(){
    int d[NUM_PROCESOS]={1, 2, 3, 4, 5};
    int p, pid, salida;
    for(p = 0; p < NUM_PROCESOS; p++){
        pid = fork();
        if(pid == -1){
            perror("Error al crear el proceso");
            exit(-1);
        }
        else if(pid == 0){
            codigo_del_proceso(p);
        }
    }
    //Esto solo lo ejecuta el padre
    for(p = 0; p < NUM_PROCESOS; p++){
        pid = wait(&salida);
        printf("Proceso %d con id = %x terminado\n", pid, salida >> 8);
    }
}
