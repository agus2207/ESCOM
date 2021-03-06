#include <sys/types.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netdb.h>
#include <strings.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>


int puerto = 7200;

int main(void)
{
   struct sockaddr_in msg_to_server_addr, client_addr;
   int s, num[2], res;
   int x;
   struct timeval timeout;
   timeout.tv_sec = 0;
   timeout.tv_usec = 250000;

   for(x = 0; x < 255; x++){

      char direccion [14] = "10.100.74.";
      char numero [4];
      printf("%d\n", x);

      sprintf(numero,"%d",x);
      strncat( direccion, numero, 10);

      printf("%s\n", direccion);

      s = socket(AF_INET, SOCK_DGRAM, 0);
      /* rellena la dirección del servidor */
      bzero((char *)&msg_to_server_addr, sizeof(msg_to_server_addr));
      msg_to_server_addr.sin_family = AF_INET;
      msg_to_server_addr.sin_addr.s_addr = inet_addr(direccion);
      msg_to_server_addr.sin_port = htons(puerto);

      setsockopt(s, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout));
      
      /* rellena la direcciòn del cliente*/
      bzero((char *)&client_addr, sizeof(client_addr));
      client_addr.sin_family = AF_INET;
      client_addr.sin_addr.s_addr = INADDR_ANY;
      
      /*cuando se utiliza por numero de puerto el 0, el sistema se encarga de asignarle uno */
      client_addr.sin_port = htons(0);
      bind(s, (struct sockaddr *)&client_addr,sizeof(client_addr));
      num[0] = 2;
      num[1] = 5; /*rellena el mensaje */

      sendto(s, (char *)num, 2 * sizeof(int), 0, (struct sockaddr *) &msg_to_server_addr, sizeof(msg_to_server_addr));
      
      /* se bloquea esperando respuesta */

      int n;
      n = recvfrom(s, (char *)&res, sizeof(int), 0, NULL, NULL);
      if(n < 0){
         if(errno == EWOULDBLOCK){
            fprintf(stderr, "No se encontro el servidor UDP\n");
            close(s);
            continue;
         }
         else{
            fprintf(stderr, "Error\n");
            close(s);
            continue;
         }
      }
      
      printf("2 + 5 = %d\n", res);

      
      close(s);
   }
}
