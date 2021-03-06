#include "PaqueteDatagrama.h"
#include "header.h"


PaqueteDatagrama::PaqueteDatagrama(struct mensaje msg, unsigned int longi, char *dir, int port){

    m1 = msg;
    //printf("Opcion: %li\n", m1.opcode);
    //printf("Nombre: %s\n", m1.name);
    
    memcpy(ip, dir, sizeof(char)*16);           

    longitud = longi;                           //variable longitud configurada
    puerto = port;     
}
PaqueteDatagrama::PaqueteDatagrama(unsigned int num){

    longitud = num;                             //variable longitud configurada
}

char * PaqueteDatagrama::obtieneDireccion(){   
    return ip;                                  //a donde se envia el datagrama o desde donde
}

unsigned int PaqueteDatagrama::obtieneLongitud(){ 
    return longitud;                              //enviados o recibidos
}
int PaqueteDatagrama::obtienePuerto(){         
    return puerto;                              //ya sea quien envia o para quien se envia
}
struct mensaje PaqueteDatagrama::obtieneDatos(){        
    return m1;
}
void PaqueteDatagrama::inicializaPuerto(int port){    //inicializa puerto
    puerto = port;
}
void PaqueteDatagrama::inicializaIp(char *dir){       //inicializa IP
    memcpy(ip, dir, sizeof(char)*16);
}
void PaqueteDatagrama::inicializaDatos(struct mensaje msg){   //inicializa los datos
    m1 = msg;
}