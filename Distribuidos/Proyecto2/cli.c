#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <strings.h>
#include <unistd.h>

int main(int argc,char *argv[])
{
   if(argc > 3){
	printf("Uso cli.out < dir > < port >\n");
	}
   
   int puerto = atoi(argv[2]);
   struct sockaddr_in msg_to_server_addr, client_addr;
   int s, num[1], res;
   s = socket(AF_INET, SOCK_DGRAM, 0);
   char c [180] = "";
   
   /* rellena la dirección del servidor */
   bzero((char *)&msg_to_server_addr, sizeof(msg_to_server_addr));
   msg_to_server_addr.sin_family = AF_INET;
   msg_to_server_addr.sin_addr.s_addr = inet_addr(argv[1]);
   msg_to_server_addr.sin_port = htons(puerto);
   
   /* rellena la direcciòn del cliente*/
   bzero((char *)&client_addr, sizeof(client_addr));
   client_addr.sin_family = AF_INET;
   client_addr.sin_addr.s_addr = INADDR_ANY;
   
   /*cuando se utiliza por numero de puerto el 0, el sistema se encarga de asignarle uno */
   client_addr.sin_port = htons(0);
   bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
	   
   /*rellena el mensaje */
   srand(time(NULL));
   int k=0,rnd=0;

   while(k<20){
   /*Envio*/
   num[0] = rand() % 35 + 1;
   sendto(s, (char *)num, sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
   k++;   }

   num[0] = 0;
   sendto(s, (char *)num, sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
   
   /* Se bloquea esperando respuesta */
   recvfrom(s, (char *)&c, sizeof(c), 0, NULL, NULL);
   printf("%s\n", c);

   sleep(20);
   close(s);
}
