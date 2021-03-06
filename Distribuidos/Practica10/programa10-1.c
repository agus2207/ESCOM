#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h> 
#include <string.h>
#include <netinet/in.h>
#include <stdlib.h> 
#include <unistd.h>
#include <arpa/inet.h>

int puerto = 7200;

int main(void)
{
   struct sockaddr_in msg_to_server_addr, client_addr;
   int s, num[2], res;

   char host[14];
   char dest[50];
   int pto;
   printf("Escribe la direccion del servidor:");
   fgets(host,sizeof(host),stdin);
   fflush(stdin);
   printf("\nEscribe el puerto:");
   scanf("%d",&pto);
   fflush(stdin);
   
   s = socket(AF_INET, SOCK_DGRAM, 0);
   /* rellena la dirección del servidor */
   bzero((char *)&msg_to_server_addr, sizeof(msg_to_server_addr));
   msg_to_server_addr.sin_family = AF_INET;
   msg_to_server_addr.sin_addr.s_addr = inet_addr(host);
   msg_to_server_addr.sin_port = htons(pto);
  
   /* rellena la direcciòn del cliente*/
   bzero((char *)&client_addr, sizeof(client_addr));
   client_addr.sin_family = AF_INET;
   client_addr.sin_addr.s_addr = INADDR_ANY;
   
   /*cuando se utiliza por numero de puerto el 0, el sistema se encarga de asignarle uno */
   client_addr.sin_port = htons(6666);
   bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
   num[0] = 2;
   num[1] = 5; /*rellena el mensaje */
   sendto(s, (char *)num, 2 * sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
   
   /* se bloquea esperando respuesta */
   recvfrom(s, (char *)&res, sizeof(int), 0, NULL, NULL);
   printf("\nServidor conectado desde ->%s:%d\n"
         "Recibiendo datos...\n", 
	      inet_ntoa(msg_to_server_addr.sin_addr),ntohs(msg_to_server_addr.sin_port));
   printf("2 + 5 = %d\n", res);
   close(s);
}

