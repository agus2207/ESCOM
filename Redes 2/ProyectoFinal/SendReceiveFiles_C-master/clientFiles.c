#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFFSIZE 1
#define	ERROR	-1
#define	PUERTO	1100

int main(int argc, char *argv[]){
	struct sockaddr_in stSockAddr;
	int sockLen;
	int Res;
	int SocketFD;
	int recibido;
	char buffer[BUFFSIZE];
	char mensaje[80];
	int totalBytesRcvd;
	int bytesRcvd;
	FILE *archivo2;
	int opcion;
	char nombre[100];
	FILE *archivo;
 	int opc=0;
 	char* cadena1="Archivos/";
 	char path[200];
    int contador = 0, encontrado = 0;
    char cadena[256], buscar[100], nombre2[100];
    char* alo="Guardar";
    struct sockaddr_in msg_to_server_addr, client_addr;
   int s, num[2], res;
   unsigned char aux[4];
   char ips[3][20];
   strcpy(ips[0],"127.0.0.1");
   strcpy(ips[1],"192.168.0.125");
   strcpy(ips[2],"192.168.0.100");
	printf("Elija la opcion: \n");
	printf("1.- Subir archivo\n");
	printf("2.- Buscar Palabra\n");
	scanf("%d",&opcion);
	switch(opcion){
		case 1:
			/*Se abre el archivo2 a enviar*/
			printf("Nombre del archivo: \n");
			scanf("%s",nombre);
			//printf("%s\n",nombre );
			
			
			strcat(path,cadena1);
			strcat(path,nombre);
			printf("%s\n",path );
			for (int i = 0; i < 3; ++i)
			{
				archivo2 = fopen(path,"rb");

			/*Se crea el socket*/
			SocketFD = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);

			/*Se configura la dirección del socket del cliente*/
			memset(&stSockAddr, 0, sizeof stSockAddr);
		 
			stSockAddr.sin_family = AF_INET;
			stSockAddr.sin_port = htons(1100);
			Res = inet_pton(AF_INET, ips[i], &stSockAddr.sin_addr);
		 
			sockLen = sizeof(stSockAddr);

			if (connect(SocketFD, (struct sockaddr *)&stSockAddr, sizeof stSockAddr) == ERROR){
				perror("Error a la hora de conectarse con el cliente");
				close(SocketFD);
				continue;
				//exit(EXIT_FAILURE);
			}

			printf("Se ha conectado con el servidor:%s\n",(char *)inet_ntoa(stSockAddr.sin_addr));
			//send(SocketFD,alo,sizeof(nombre),0);
			send(SocketFD,nombre,sizeof(nombre),0);
			/*Se envia el archivo2*/
			while(!feof(archivo2)){
				fread(buffer,sizeof(char),BUFFSIZE,archivo2);
				if(send(SocketFD,buffer,BUFFSIZE,0) == ERROR)
					perror("Error al enviar el arvhivo:");
			}
			
			read(SocketFD,mensaje,sizeof(mensaje));
			printf("\nConfirmación recibida:\n%s\n",mensaje);
			
			read(SocketFD,mensaje,sizeof(mensaje));
			printf("\nMD5SUM:\n%s\n",mensaje);
			
			fclose(archivo2);
			close(SocketFD);
			}
			
			break;
		case 2:
			

		    printf("\n\nIngrese el nombre o ubicacion del archivo, incluyendo punto y formato: ");
		    //fflush(stdin);
		    scanf("%s", nombre2);
		    archivo = fopen(nombre2,"r");
		    
		    
			break;
	
	}//End switch
	return 0;
}//End main

