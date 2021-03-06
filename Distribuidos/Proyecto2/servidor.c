#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h> 
#include <string.h>
#include <netinet/in.h>
#include <stdlib.h> 
#include <unistd.h>
#include <arpa/inet.h>
#include <pthread.h>
#include <sys/time.h>
#include <errno.h>

struct hilo_envia{
    int sock;
    int data;
};

void *enviar(void* sc){
    //printf("Logre entrar al hilo de envio\n");
    int recibe, clilen;
    struct sockaddr_in msg_to_server_addr;
    bzero((char *)&msg_to_server_addr, sizeof(msg_to_server_addr));
    msg_to_server_addr.sin_family = AF_INET;
    msg_to_server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    msg_to_server_addr.sin_port = htons(6666);
    struct hilo_envia *p2 = sc;
    //printf("Este es el socket: %d\n", sock);
    sendto(p2->sock, (char *)&p2->data, sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
    //printf("Resultado de recvfrom: %d\n", a);
    //printf("Envie el numero (hilo): %d \n", p2->data);
    pthread_exit(NULL);
}

void *recibir(void* s){
    //printf("Logre entrar al hilo\n");
    int recibe, clilen;
    struct sockaddr_in msg_to_client_addr;
    clilen = sizeof(msg_to_client_addr);
    //int sock = *(int*)s;
    struct hilo_envia *p2 = s;
    //printf("Este es el socket: %d\n", p2->sock);
    recvfrom(p2->sock, &p2->data, sizeof(int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
    //printf("Resultado de recvfrom: %d\n", a);
    //printf("Recibi el numero (hilo): %d \n", p2->data);
    pthread_exit(&p2->data);
}

int main(int argc, char *argv[]){

    int sr, *resultado, count = 0, clilen, res, limite, count2 = 0;
    struct sockaddr_in server_addr, msg_to_client_addr;
    pthread_t operacion;
    double porcentaje = 0.0, final = 0.0;

    sr = socket(AF_INET, SOCK_DGRAM, 0);
    
    bzero((char *)&server_addr, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(7200);
    clilen = sizeof(msg_to_client_addr);
    bind(sr, (struct sockaddr *)&server_addr, sizeof(server_addr));

    for(;;){
        struct hilo_envia h1 = {sr, 0};
        pthread_create(&operacion, NULL, recibir, (void*) &h1);
        pthread_join(operacion, (void**)&resultado);
        limite = *(int*)resultado;
        porcentaje = 100.0/limite;
        final = 0.0;
        //printf("Porcentaje = %.2f\n", porcentaje);
        for(int i = 0; i < limite+1; i++){
            struct hilo_envia h1 = {sr, 0};
            pthread_create(&operacion, NULL, recibir, (void*) &h1);
            pthread_join(operacion, (void**)&resultado);
            res = *(int*)resultado;
            printf("Recibi el numero: %d \n", res);
            count++;
            if(res == 0){
                for(int j = count; j <= limite; j++){
                    //printf("Me falto: %d\n", j); 
                    struct hilo_envia h2 = {sr, j};
                    pthread_create(&operacion, NULL, enviar, (void*) &h2);
                    pthread_join(operacion, NULL);   
                }
                //printf("Termine de recibir\n");
                count = 0;
                count2 = 0;
                printf("Porcentaje recibido: %.2f\n", final);
                break;
            }
            else if(res == count){
                count2++;
                final = porcentaje*count2;
                //printf("Lllevo hasta el momento esto recibido: %.2f\n", final);
            }
            else if(count != res){
                for(int i = count; i < res; i++){
                    //printf("Me falta: %d\n", i);
                    struct hilo_envia h2 = {sr, i};
                    pthread_create(&operacion, NULL, enviar, (void*) &h2);
                    pthread_join(operacion, NULL);
                }
                count = res;
            }
        }
    }
}