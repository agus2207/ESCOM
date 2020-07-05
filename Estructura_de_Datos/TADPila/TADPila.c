/*
IMPLEMENTACIONES DE LA LIBRERIA DEL TAD PILA DINÁMICA con el TAD LISTA
AUTOR: Galindo Reyes Agustin (C) Noviembre 2016
VERSIÓN: 1.0

DESCRIPCIÓN: TAD pila.
Estructura de datos en la que se cumple:
1. Los elementos se añaden y se remueven por un solo extremo.
2. Este extremo es llamado “tope” de la pila.

OBSERVACIONES: Hablamos de una Estructura de datos dinámica cuando se le asigna memoria a medida que es necesitada,
durante la ejecución del programa. En este caso la memoria no queda fija durante la compilación.
*/

//LIBRERAS
#include <stdlib.h>
#include "..\TADLista\TADLista.c"
#include "TADPila.h"

//DEFINICIÓN DE FUNCIONES


/*
void Initialize(pila *s);
Descripción: Da Inicio a la pila
Recibe: una referencia a la pila que se desea iniciar (s)
Observaciones: si se crea la pila y no se hace referencia a ella (s) esto provocara un error

*/
void Initialize(pila *s)
{
}

/*
void Push(pila *s, elemento e);
Descripción: Introduce un elemento a la pila
Recibe: una referencia a la pila que se desea usar y el elemento a introducir
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.
Ademas no se valida si el malloc() pudo o no apartar memoria, se idealiza que siempre funciona bien
y no se acaba la memoria
*/
void Push(pila *s, elemento e)
{
    //Llamado de la función add
    Add(s, e);
}


/*
void Pop(pila *s);
Descripción: Saca un elemento de la pila
Recibe: la referencia de la pila a operar
Devuelve: el elemento que se saco de la pila
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.
Ademas no se valida si la pila esta vacia antes de desempilar (causa error desempilar si esta esta vacía),
tampoco se valida si free() pudo o no liberar la memoria, se idealiza que siempre funciona bien
*/
elemento Pop (pila *s)
{
    elemento e;
    e = Final(s)->e;
    Remove(s, s->final);
    return e;
}

/*
boolean Empty(pila *s);
Descripción: Evalua si la pila esta vacia
Recibe: un apuntador entero a s que hace referencia a la pila
Devuelve: un estado de verdadero o falso sea el casp
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.
*/
boolean Empty(pila *s)
{
}

/*
elemento Top(pila *s);
Descripción: Obtiene el elemento en la cima de la pila sin sacarlo
Recibe: recibe un apuntador entero referenciando a la pila
Devuelve: el elemento en la cima de la pila
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.
Ademas no se valida si la pila esta vacia antes de consultar al elemnto del tope (causa error si esta esta vacía).

*/
elemento Top(pila *s)
{
	return Final(s)->e;
}

/*
int ValueTop(pila *s);
Descripción: Evalua el tamaño de la pila
Recibe: recibe un apuntador entero referenciando a la pila
Devuelve: un entero que representa el tamaño de la pila
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.

NOTA: El value top nos devuelve el tamaño de la Pila lo cual es posible utilizando la función Size que ya
existe en la lista
*/
int Value_Top(pila *s)
{
	return Size(s);
}

/*
void Destroy(pila *s);
Descripción: Elimina pila (Borra a todos los elementos en a la pila de memoria)
Recibe: int *s (Referencia a la pila "s" a operar)
Devuelve:
Observaciones: El usuario a creado una pila y s tiene la referencia a ella, s ya ha sido inicializada.

NOTA: El Destroy ya existe en el TAD Lista no es necesario recrearla, simplemente hay que renombrarla en el header
*/
void Destroy(pila *s)
{
}
