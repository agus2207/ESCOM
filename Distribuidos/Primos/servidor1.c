#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h> 
#include <string.h>
#include <netinet/in.h>
#include <stdlib.h> 
#include <unistd.h>
#include <arpa/inet.h>
#include <stdbool.h>

int puerto = 7200;

int main(void)
{
    unsigned long int primo, extremo1, extremo2, i = 0;
    int s, res, clilen;
    struct sockaddr_in server_addr, msg_to_client_addr;
    int resultado = 1;
    
    s = socket(AF_INET, SOCK_DGRAM, 0);
    
    /* se asigna una direccion al socket del servidor*/
    bzero((char *)&server_addr, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(puerto);
    bind(s, (struct sockaddr *)&server_addr, sizeof(server_addr));
    clilen = sizeof(msg_to_client_addr);
    while(1) {
        recvfrom(s, (char *) &primo, sizeof(unsigned long int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
        recvfrom(s, (char *) &extremo1, sizeof(unsigned long int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
        recvfrom(s, (char *) &extremo2, sizeof(unsigned long int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
        printf("Recibi: %ul, %ul, %ul\n", primo, extremo1, extremo2);
        for(i=extremo1; i <= extremo2; i++){
            if(primo%i == 0){
                resultado = 0;
            }
        }
        printf("Mandare: %d\n", resultado);
        printf("\nCliente conectado desde ->%s:%d\n"
            "Recibiendo datos...\n", 
	        inet_ntoa(msg_to_client_addr.sin_addr),ntohs(msg_to_client_addr.sin_port));
        /* envía la petición al cliente. La estructura msg_to_client_addr contiene la dirección socket del cliente */
        sendto(s, (char *)&resultado, sizeof(int), 0, (struct sockaddr *)&msg_to_client_addr, clilen);
    }    
}