#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h> 
#include <string.h>
#include <netinet/in.h>
#include <stdlib.h> 
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/time.h>
#include <pthread.h>
#include <errno.h>

struct hilo_envia{
    int socket;
    int dato;
};

void *enviar(void* sc){
    //printf("Logre entrar al hilo de envio\n");
    int recibe, clilen;
    struct sockaddr_in msg_to_server_addr;
    bzero((char *)&msg_to_server_addr, sizeof(msg_to_server_addr));
    msg_to_server_addr.sin_family = AF_INET;
    msg_to_server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    msg_to_server_addr.sin_port = htons(7200);
    struct hilo_envia *p2 = sc;
    //printf("Este es el socket: %d\n", sock);
    sendto(p2->socket, (char *)&p2->dato, sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
    //printf("Resultado de recvfrom: %d\n", a);
    //printf("Envie el numero (hilo): %d \n", p2->dato);
    pthread_exit(NULL);
}

void *recibir(void* sr){
    struct timeval timeout;
    timeout.tv_sec = 2;
    timeout.tv_usec = 500000;
    //printf("Logre entrar al hilo de recibir\n");
    int recibe, clilen;
    struct sockaddr_in msg_to_client_addr;
    clilen = sizeof(msg_to_client_addr);
    int sock = *(int*)sr;
    setsockopt(sock, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
    //printf("Este es el socket: %d\n", sock);
    int n = recvfrom(sock, &recibe, sizeof(int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
    if (n < 0){
        if (errno == EWOULDBLOCK)
            recibe = 0;
        else
            fprintf(stderr, "Error en recvfrom\n");
    }
    //printf("Resultado de recvfrom: %d\n", a);
    if(recibe > 0)
        printf("Falto el numero: %d \n", recibe);
    pthread_exit(&recibe);
} 

int main(void){

    struct sockaddr_in msg_to_server_addr, client_addr;
    int sc, res, numero, i, *resultado, count = 0;
    pthread_t operacion;
    sc = socket(AF_INET, SOCK_DGRAM, 0);

    printf("Introduzca un numero: ");
    scanf("%d",&numero);
    fflush(stdin);

    double final = 0.0, porcentaje;

    if(numero > 0)
        porcentaje = 100.0/numero;
    else    
        porcentaje = 0.0;

    bzero((char *)&client_addr, sizeof(client_addr));
    client_addr.sin_family = AF_INET;
    client_addr.sin_addr.s_addr = INADDR_ANY;

    client_addr.sin_port = htons(6666);

    bind(sc, (struct sockaddr *)&client_addr,sizeof(client_addr));
    
    struct hilo_envia inicio = {sc, numero};
    pthread_create(&operacion, NULL, enviar, (void*) &inicio);
    pthread_join(operacion, NULL);

    struct hilo_envia h[numero];

    for(i = 0; i < numero; i++){
        h[i].socket = sc;
        h[i].dato = i+1;
        pthread_create(&operacion, NULL, enviar, (void*) &h[i]);
        pthread_join(operacion, NULL);
    }

    if(numero != 0){
        struct hilo_envia h2 = {sc, 0};
        pthread_create(&operacion, NULL, enviar, (void*) &h2);
        pthread_join(operacion, NULL); 
    }

    for(;;){
        pthread_create(&operacion, NULL, recibir, (void*) &sc);
        pthread_join(operacion, (void**)&resultado);
        int n = *(int*)resultado;
        if(n == 0){
            final = 100.0 - final;
            if(numero != 0)
                printf("Mande un total del: %.2f\n", final);
            final = 0.0;
            break;
        }
        else{
            count++;
            final = porcentaje*count;
        }
    }

    close(sc);
}