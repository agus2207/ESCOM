#include <iostream>
#include <string.h>
#include "SocketDatagrama.h"
using namespace std;

int main(int arg, char *argv[]){
	int puerto = 7200,tam_mensaje = 10;
	char num[2];
	//char *respuesta = new char[10];
	int respuesta;
	
	num[0] = '4';
   	num[1] = '7';
	SocketDatagrama socket_datagrama(0);
	PaqueteDatagrama PD_envia((char *)&num,2*sizeof(int), argv[1], puerto); //dato, tamaño, ip, puerto
	socket_datagrama.envia(PD_envia);


	PaqueteDatagrama PD_recibe(sizeof(int));
	socket_datagrama.recibe(PD_recibe);
	respuesta = atoi(PD_recibe.obtieneDatos());
	cout<<respuesta<<endl;

	

	return 0;
}