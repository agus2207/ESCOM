#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <sstream>
#include "SocketDatagrama.h"
using namespace std;

int main(){
	int puerto = 7200,tam_mensaje = 2;
	char *num = new char[2];
	int num1, num2, res;
	char *respuesta = new char[10];
	SocketDatagrama socket_datagrama(puerto);
	socket_datagrama.setBroadcast();
	PaqueteDatagrama PD_recibe(tam_mensaje);


	while(1){
		socket_datagrama.recibe(PD_recibe);

		num = PD_recibe.obtieneDatos();
		cout<<"El num es: "<<num[0]<<" , "<< num[1]<<endl;
	
		//num = PD_recibe.obtieneDatos();
		//char 
		num1 = num[0] - '0';
		num2 = num[1] - '0';
		res = num1 + num2;
		stringstream ss;
		ss << res;
		string temp = ss.str();
		const char* temp2 = temp.c_str();
		respuesta = strdup(temp2);
		PaqueteDatagrama PD_envia(respuesta, 10*sizeof(char), PD_recibe.obtieneDireccion(), PD_recibe.obtienePuerto());
		socket_datagrama.envia(PD_envia);
		//cout<<res<<endl;
	}
	free(num);
	return 0;
}