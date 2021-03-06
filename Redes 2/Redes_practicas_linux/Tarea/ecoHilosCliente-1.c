#include <fcntl.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <stdio.h>
#include <netinet/in.h>
#include <resolv.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

int main(int argv, char **argc){
	int host_port = 1101;
	char *host_name = "127.0.0.1";
	struct sockaddr_in my_addr;
	char buffer[50];
	int bytecount;
	int buffer_len = 0;
	int hsock;
	int *p_int;
	int err;

	/********** Se crea el socket *************/
	hsock = socket(AF_INET, SOCK_STREAM, 0);
	if(hsock == -1){
		printf("Error en socket %d\n", errno);
		goto FINISH;
	}

	p_int = (int*)malloc(sizeof(int));
	*p_int = 1;

	/********** Asignamos opciones del socket ****************/
	if((setsockopt(hsock, SOL_SOCKET, SO_REUSEADDR,(char*)p_int, sizeof(int)) == -1) || (setsockopt(hsock, SOL_SOCKET, SO_KEEPALIVE,(char*)p_int, sizeof(int)) == -1)){
		printf("Error al asignar opciones de socket: %d\n", errno);
		free(p_int);
		goto FINISH;
	}

	free(p_int);

	/******** Asignamos dirección y puerto **********/
	my_addr.sin_family = AF_INET;
	my_addr.sin_port = htons(host_port);
	memset(&(my_addr.sin_zero),0,8);
	my_addr.sin_addr.s_addr = inet_addr(host_name);

	/********** Conectamos el socket *********/
	if(connect(hsock, (struct sockaddr*)&my_addr, sizeof(my_addr)) == -1){
		if((err = errno) != EINPROGRESS){
			fprintf(stderr, "Error al conectar el socket, %d\n", errno);
			goto FINISH;
		}
	}

	buffer_len = 50;
	memset(buffer, ' ', buffer_len); //Limpiamos el buffer
	while(strcmp(buffer, "SALIR") != 0){
		printf("Buffer: %s\n",buffer);
	 	memset(buffer, ' ', buffer_len); //Limpiamos el buffer
	 	printf("Buffer memset: %s\n",buffer);
		printf("Ingrese algun texto (presione enter para enviar)\n");
		fgets(buffer, 50, stdin);

		buffer[strlen(buffer)-1] = '\0';

		if((bytecount = send(hsock, buffer, strlen(buffer),0)) == -1){
			fprintf(stderr, "Error enviando datos %d\n", errno);
			goto FINISH;
		}

		printf("Bytes enviados: %d\n", bytecount);

		if((bytecount = recv(hsock, buffer, buffer_len,0)) == -1){
			fprintf(stderr, "Error al recibir datos: %d\n", errno);
			goto FINISH;
		}

		printf("Bytes recibidos: %d\nCadena: %s\n\n", bytecount, buffer);
	}

	close(hsock);
	FINISH:
	;
}
