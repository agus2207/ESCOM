#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <netinet/in.h>
#include <resolv.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <pthread.h>
#include <stdio.h>

void *socketHandler(void *);

int main(int argc, char**argv){
    int host_port = 1101;
    struct sockaddr_in my_addr;
    int hsock;
    int *p_int;
    int err;
    socklen_t addr_size = 0;
    int *csock;
    struct sockaddr_in sadr;
    pthread_t thread_id = 0;
    hsock = socket(AF_INET, SOCK_STREAM, 0);
    if(hsock == -1){
        printf("Error en socket\n");
        goto FINISH;
    }
    p_int = (int *)malloc(sizeof(int));
    *p_int = 1;
    if((setsockopt(hsock, SOL_SOCKET, SO_REUSEADDR, (char*)p_int, sizeof(int))==-1) || (setsockopt(hsock, SOL_SOCKET, SO_KEEPALIVE, (char*)p_int, sizeof(int)) == -1)){
        printf("Error al asignar opciones de socket: %d\n", errno);
        free(p_int);
        goto FINISH;
    }
    free(p_int);
    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(host_port);
    memset(&(my_addr.sin_zero), 0, 8);
    my_addr.sin_addr.s_addr = INADDR_ANY;
    if(bind(hsock, (struct sockaddr*)&my_addr, sizeof(my_addr)) == -1){
        fprintf(stderr, "Error en el ligando el socket: %d\n", errno);
        goto FINISH;
    }
    if(listen(hsock, 10) == -1){
        fprintf(stderr, "Error en el listen: %d\n", errno);
        goto FINISH;
    }
    addr_size = sizeof(struct sockaddr_in);
    for(;;){
        printf("Esperando conexion\n");
        csock = (int*)malloc(sizeof(int));
        if((*csock == acept(hsock, (struct sockaddr*)&sadr, &addr_size)) == -1){
            printf("Recibiendo desde: %s\n", inet_ntoa(sadr.sin_addr));
            pthread_create(&thread_id, 0, &socketHandler, (void*)csock);
            pthread_detach(thread_id);
        }
        else
            fprintf(stderr, "Error al establecer conexion: %d\n", errno);
    }
    FINISH:
    ;
}

void* socketHandler(void* ip){
    int *csock = (int*)ip;
    
}