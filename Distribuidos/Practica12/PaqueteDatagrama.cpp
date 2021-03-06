#include "PaqueteDatagrama.h"


PaqueteDatagrama::PaqueteDatagrama(char *cadena, unsigned int longi, char *dir, int port){
    datos = new char[longi];                    //crea un arreglo de caracteres de forma dinamica
    //printf("OK 1\n");
    memcpy(datos, cadena, sizeof(char)*longi);  //al arreglo recien creado le pasa el Mensaje
                                                //que esta almacenado en cadena, y tiene una longitud
                                                //longi
    //printf("OK 2\n");
    //printf("dir : %s\n", dir);
    memcpy(ip, dir, sizeof(char)*16);           //a la variable ip se le pasa la direccion
                                                //almacenada en dir, con una longitud de 16
    //printf("OK 3\n");
    longitud = longi;                           //variable longitud configurada
    puerto = port;                              //variable puerto configurada
    //operation = opID;
    //printf("\noperation : %d", operation);
}
PaqueteDatagrama::PaqueteDatagrama(unsigned int num){
    datos = new char[num];                      //crea un arreglo de caracteres de forma dinamica
                                                //dado que no hay informacion, queda vacio
    //printf("\nOK A");
    longitud = num;                             //variable longitud configurada
    //printf("LONGITUD %d\n", longitud);
    //printf("\nOK B");
}

PaqueteDatagrama::~PaqueteDatagrama(){          //DESTRUCTOR: ELIMINA LOS SEGMENTOS DINAMICOS
    delete(datos);
}

char * PaqueteDatagrama::obtieneDireccion(){    //GETTER: devuelve la direccion IP
    return ip;                                  //a donde se envia el datagrama o desde donde
}

unsigned int PaqueteDatagrama::obtieneLongitud(){ //GETTER: devuelve la longitud de datos
    return longitud;                              //enviados o recibidos
}
int PaqueteDatagrama::obtienePuerto(){          //GETTER: devuelve el numero de puerto del host remoto
    return puerto;                              //ya sea quien envia o para quien se envia
}

int PaqueteDatagrama::obtieneOp(){          //GETTER: devuelve el numero de puerto del host remoto
    return operation;                              //ya sea quien envia o para quien se envia
}

char *PaqueteDatagrama::obtieneDatos(){         //GETTER: devuelve lo almacenado en datos
    return datos;
}
void PaqueteDatagrama::inicializaPuerto(int port){    //inicializa puerto
    puerto = port;
}
void PaqueteDatagrama::inicializaIp(char *dir){       //inicializa IP
    memcpy(ip, dir, sizeof(char)*16);
}
void PaqueteDatagrama::inicializaDatos(char *data){   //inicializa los datos
    memcpy(datos, data, longitud);
}

void PaqueteDatagrama::inicializaOp(int oper){    //inicializa puerto
    operation = oper;
}
