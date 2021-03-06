#ifndef PAQUETE_DATAGRAMA_H_
#define PAQUETE_DATAGRAMA_H_
#include <new>
#include <cstring>
#include <stdio.h>
#include "header.h"

using namespace std;

class PaqueteDatagrama {
    public:
        PaqueteDatagrama(struct mensaje, unsigned int, char *, int );
        PaqueteDatagrama(unsigned int );
        char *obtieneDireccion();
        unsigned int obtieneLongitud();
        int obtienePuerto();
        struct mensaje obtieneDatos();
        void inicializaPuerto(int);
        void inicializaIp(char *);
        void inicializaDatos(struct mensaje);
    private:
        struct mensaje m1;
        char ip[16];//Almacena la IP
        unsigned int longitud;//Almacena la longitude de la cadena de datos
        int puerto;//Almacena el puerto
};
#endif