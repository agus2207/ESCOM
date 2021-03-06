#include "header.h"
#include "File.h"
#include "SocketDatagrama.h"
#include "PaqueteDatagrama.h"
#include <iostream>
#include <stdlib.h>
#include <string>

using namespace std;

int main(int argc, char *argv[]) {

    struct mensaje cliente_msg;
    SocketDatagrama socket(6666);
    cout << "CLIENTE" << "\n";
    int operacion;
    char origen[255];
    char destino[1024];
    cout << "Bienvenido al cliente ingrese una opcion:" <<endl;
    cout << "1. Enviar archivo\n2.Buscar archivo en el servidor" << endl;
    cin >> operacion;
    while(operacion == 0 || operacion > 2){
        cout << "Opcion invalida\nIntente de nuevo:" << endl;
        cin >> operacion;
    }
    if(operacion == 1){
        cout << "Escriba la ruta del archivo a enviar:" << endl;
        cin >> origen;
        cout << "Escriba el nombre del archivo destino:" << endl;
        cin >> destino;
    }
    else if(operacion == 2){
        cout << "Escriba el nombre del archivo a buscar:" << endl;
        cin >> origen;
        cout << "Escriba la ruta del archivo destino:" << endl;
        cin >> destino;
    }

    cliente_msg.opcode = READ;
    memcpy(cliente_msg.name, origen, sizeof(cliente_msg.name));
    PaqueteDatagrama paquete(cliente_msg, sizeof(cliente_msg), (char *) "127.0.0.1", 7200);
    socket.envia(paquete);
    socket.recibe(paquete);
    cliente_msg = paquete.obtieneDatos();
    //printf("Estado: %li\n", cliente_msg.result);

    if(cliente_msg.result != -1){
        cliente_msg.opcode = WRITE;
        memcpy(cliente_msg.name, origen, sizeof(cliente_msg.name));
        memcpy(cliente_msg.data, destino, sizeof(cliente_msg.data));
        PaqueteDatagrama paquete(cliente_msg, sizeof(cliente_msg), (char *) "127.0.0.1", 7200);
        socket.envia(paquete);
        socket.recibe(paquete);
        cliente_msg = paquete.obtieneDatos();
        //printf("Estado: %li\n", cliente_msg.result);
        if(cliente_msg.result != -1 && operacion == 1)
            printf("ARCHIVO ENVIADO AL SERVIDOR\n");
        else if(cliente_msg.result != -1 && operacion == 2)
            printf("ARCHIVO RECIBIDO CON EXITO\n");
        else
            printf("OCURRIO ALGUN ERROR EN EL SERVIDOR\n");
    }

    else
        printf("OCURRIO ALGUN ERROR EN EL SERVIDOR\n");
}