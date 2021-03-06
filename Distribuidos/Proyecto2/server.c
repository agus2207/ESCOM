#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <netdb.h>
#include <pthread.h>
#include "methods.h"

int puerto;
void udp_bb();

int main(int argc,char *argv[])
{
	if(argc > 4){
		printf("Uso server.out < port > <maximo clientes> <t_espera>\n");
		return 0;
	}
	
	puerto = atoi(argv[1]);
	int max_cli = atoi(argv[2]);
	
	int i;
	pthread_attr_t attrib;
	pthread_t hilos[max_cli];
	pthread_attr_init(&attrib);
	pthread_attr_setdetachstate(&attrib,PTHREAD_CREATE_DETACHED);

	for(i=0;i < max_cli ;i++){
		pthread_create(&hilos[i],&attrib,(void *) udp_bb,NULL);
	}
	sleep(atoi(argv[3]));
}

void udp_bb(){
	char c [180] = "";
	struct arr_ind * digitos = NULL;
	int num[1];
	int s, clilen;
	struct sockaddr_in server_addr, msg_to_client_addr;   
	s = socket(AF_INET, SOCK_DGRAM, 0);
	   
	/* se asigna una direccion al socket del servidor*/
	bzero((char *)&server_addr, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.s_addr = INADDR_ANY;
	server_addr.sin_port = htons(puerto);
	bind(s, (struct sockaddr *)&server_addr, sizeof(server_addr));
	clilen = sizeof(msg_to_client_addr);
   
	while(1) {
		recvfrom(s, (char *) num, sizeof(int), 0, (struct sockaddr *)&msg_to_client_addr, &clilen);
		digitos = agregar_valor(num[0],digitos);
		printf("Se recibio %d desde %s:%d\n",num[0],inet_ntoa(msg_to_client_addr.sin_addr), 
			ntohs(msg_to_client_addr.sin_port));
		/* envía la petición al cliente. La estructura msg_to_client_addr contiene la dirección socket del cliente */
		if(num[0] == 0){
			faltantes(digitos,c);
			sendto(s, (char *)&c, sizeof(c), 0, (struct sockaddr *)&msg_to_client_addr, clilen);
			c[0] = '\0';
		}
		
	}
}