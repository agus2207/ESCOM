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
    struct sockaddr_in msg_to_server_addr1, msg_to_server_addr2, msg_to_server_addr3, client_addr;
    int s, num[2], res;
    int respuesta;

   /* datos a enviar*/
    unsigned long int primo = 4294967291;
    unsigned long int inicio = 2;
    unsigned long int parte1, parte2, parte3;
    parte1 = primo*0.33;
    parte2 = parte1*2;
    unsigned long int residuo = primo-parte1-parte2;
    parte3 = parte1+parte2+residuo;
   
    s = socket(AF_INET, SOCK_DGRAM, 0);
    /* rellena la dirección del servidor 1*/
    bzero((char *)&msg_to_server_addr1, sizeof(msg_to_server_addr1));
    msg_to_server_addr1.sin_family = AF_INET;
    msg_to_server_addr1.sin_addr.s_addr = inet_addr("127.0.0.1");
    msg_to_server_addr1.sin_port = htons(7200);

    /* rellena la direccion del servidor 2*/
    bzero((char *)&msg_to_server_addr2, sizeof(msg_to_server_addr2));
    msg_to_server_addr2.sin_family = AF_INET;
    msg_to_server_addr2.sin_addr.s_addr = inet_addr("127.0.0.1");
    msg_to_server_addr2.sin_port = htons(7201);

    /* rellena la direccion del ervidor 3*/
    bzero((char *)&msg_to_server_addr3, sizeof(msg_to_server_addr3));
    msg_to_server_addr3.sin_family = AF_INET;
    msg_to_server_addr3.sin_addr.s_addr = inet_addr("127.0.0.1");
    msg_to_server_addr3.sin_port = htons(7202);
    
    /* rellena la direcciòn del cliente*/
    bzero((char *)&client_addr, sizeof(client_addr));
    client_addr.sin_family = AF_INET;
    client_addr.sin_addr.s_addr = INADDR_ANY;

    /*Envio al servidor 1*/
    client_addr.sin_port = htons(6666);
    bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
    sendto(s, (char *)&primo, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr1, sizeof(msg_to_server_addr1));
    sendto(s, (char *)&inicio, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr1, sizeof(msg_to_server_addr1));
    sendto(s, (char *)&parte1, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr1, sizeof(msg_to_server_addr1));

    

    /*Envio al servidor 2*/
    client_addr.sin_port = htons(6666);
    bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
    sendto(s, (char *)&primo, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr2, sizeof(msg_to_server_addr2));
    sendto(s, (char *)&parte1, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr2, sizeof(msg_to_server_addr2));
    sendto(s, (char *)&parte2, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr2, sizeof(msg_to_server_addr2));

    

    /*Envio al servidor 3*/
    client_addr.sin_port = htons(6666);
    bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
    sendto(s, (char *)&primo, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr3, sizeof(msg_to_server_addr3));
    sendto(s, (char *)&parte2, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr3, sizeof(msg_to_server_addr3));
    sendto(s, (char *)&parte3, sizeof(unsigned long int), 0, (struct sockaddr *) &msg_to_server_addr3, sizeof(msg_to_server_addr3));

    /*Respuestas de los servidores*/
    recvfrom(s, (char *)&respuesta, sizeof(int), 0, NULL, NULL);
    printf("\nServidor conectado desde ->%s:%d\n"
            "Recibiendo datos...\n", 
            inet_ntoa(msg_to_server_addr1.sin_addr),ntohs(msg_to_server_addr1.sin_port));
    printf("%ul es primo ? = %d\n", primo, respuesta);

    recvfrom(s, (char *)&respuesta, sizeof(int), 0, NULL, NULL);
    printf("\nServidor conectado desde ->%s:%d\n"
            "Recibiendo datos...\n", 
            inet_ntoa(msg_to_server_addr2.sin_addr),ntohs(msg_to_server_addr2.sin_port));
    printf("%ul es primo ? = %d\n", primo, respuesta);

    recvfrom(s, (char *)&respuesta, sizeof(int), 0, NULL, NULL);
    printf("\nServidor conectado desde ->%s:%d\n"
            "Recibiendo datos...\n", 
            inet_ntoa(msg_to_server_addr3.sin_addr),ntohs(msg_to_server_addr3.sin_port));
    printf("%ul es primo ? = %d\n", primo, respuesta);

    close(s);
}