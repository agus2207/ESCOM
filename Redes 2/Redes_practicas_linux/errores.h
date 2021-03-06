#ifndef _ERRORES_
#define _ERRORES_
#include <stdio.h>
#define error_fatal(codigo, texto) do{\
    fprintf(stderr, "%s: %d: Error %s-%s\n",\
    _FILE_, _LINE_, texto, strerror(codigo));\
    abort();\
}while(0);
#endif