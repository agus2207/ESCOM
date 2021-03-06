#include "Solicitud.h"
#include <iostream>
#include <string>
//#include <cstdlib>
using namespace std;


Solicitud::Solicitud(){
	socketlocal = new SocketDatagrama(0);
	cout << "SOCKET CREATED" << "\n";
}

char * Solicitud::doOperation(char *IP, int puerto, int operationId, char *arguments){
	size_t len = strlen(arguments);
	char respuesta[sizeof(arguments)*len];
	char id = operationId + '0';
	arguments[len] = id;
	arguments[len + 1] = '\0';
	cout << "ARG: " << arguments << endl;


	PaqueteDatagrama paqueteSolicitud(arguments, (len+1), IP, puerto);
	char *error_info = (char *)"SERVER: TRY AGAIN LATER...";
	cout << "SENDING PACKAGE TO:" << paqueteSolicitud.obtieneDireccion() << ":" << paqueteSolicitud.obtienePuerto() << endl;
	socketlocal->envia(paqueteSolicitud);
	cout << "PACKAGE DELIVERED" << endl;

	int r;
	int len2 = sizeof(arguments);
	PaqueteDatagrama paqueteRespuesta(len2 * sizeof(char));

	for(int n = 0; n < 7; n++){
		cout << "WAITING..." << "\n";
		r = socketlocal->recibeTimeOut(paqueteRespuesta, 2, 5);
		if(r == -1){
			std::cout << "\nGOT NO ANSWER: TIMEOUT" << '\n';
			std::cout << "\nFORWARDING..." << '\n';
			socketlocal->envia(paqueteSolicitud);
		}
		else
			break;
	}

	if(r == -1){
		cout << "RECEIVED = NO ANSWER" << "\n";
		return error_info;
	}
	else{
		cout << "MESSAGE FROM: " << paqueteRespuesta.obtieneDireccion() << ":" << paqueteRespuesta.obtienePuerto() << "\n";
		cout << "OBTIENE DATOS: " << paqueteRespuesta.obtieneDatos() << '\n';
		memcpy((char *)&respuesta, paqueteRespuesta.obtieneDatos(), sizeof(respuesta));
		cout << "RESPUESTA: " << respuesta << endl;
		arguments = respuesta;
		return arguments;
	}


}
