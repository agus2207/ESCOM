/*
IMPLEMENTACIONES DE LA LIBRERIA DEL TAD LISTA (TADLista.h)
AUTOR: Edgardo Adri�n Franco Mart�nez (C) Octubre 2016
VERSI�N: 1.0

DESCRIPCI�N: TAD lista o (list)
Estructura de datos en la que se cumple:
Los elementos se consultan, a�aden y se remueven con base en posiciones
dentro de un arreglo lineal el cual cuenta con un frente o cabeza
y un final o cola.

OBSERVACIONES: Hablamos de una Estructura de datos din�mica de una lista
doblemente ligada.

Frente                                                       Final
      ******    ******    ******    ******    ******    ******
NULL<-*    * <- *    * <- *    * <- *    * <- *    * <- *    *
      * e1 *    * e2 *    * e3 *    * e4 *    * e5 *    * e6 *
      *    * -> *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      * -> *    * -> *    * -> *    * -> *    * -> NULL
      ******    ******    ******    ******    ******    ******


COMPILACI�N PARA GENERAR EL C�DIGO OBJETO: gcc -c TADLista.c
*/

//LIBRERAS
#include<stdio.h>
#include<stdlib.h>
#include "TADLista.h"

//DEFINICI�N DE FUNCIONES

/***************************************************
Operaciones de construcci�n
***************************************************/
/*
void Initialize(lista *l)
Descripci�n: Inicializar pila (Iniciar una pila para su uso)
Recibe: int *s (Referencia a la pila "s" a operar)
Devuelve:
Observaciones: El usuario a creado una pila y s tiene la referencia a ella,
si esto no ha pasado se ocasionara un error.
*/
void Initialize(lista *l)
{
    l->frente = NULL;
    l -> final = NULL;
    l->tam = 0;
    return;
}

void Destroy(lista *l)
{
    posicion aux;
    while(!Empty(l))
    {
        aux=l->final->adelante;
        free(l->final);
        l->tam--;
    }
}

/***************************************************
Operaciones de posicionamiento y busqueda
***************************************************/
posicion Final(lista *l)
{
    return l->final;
}

posicion First(lista *l)
{
    return l->frente;
}

posicion Following(lista *l, posicion p)
{
    if(ValidatePosition(l,p))
    {
        printf("\nFollowing tiene %d", p->e.numero);
        return p->adelante;
    }
    else
    {
        printf("ERROR: Following(posicion no valida o  lista vacia)");
        exit(1);
    }
}

posicion Previus(lista *l, posicion p)
{

    if(ValidatePosition(l,p))
    {
        return p->atras;
    }
    else
    {
        printf("ERROR: Previus(posicion no valida o  lista vacia)");
        exit(1);
    }
}

posicion Search(lista *l, elemento e)
{
    posicion r=NULL,aux;

    if (l->tam>0)
    {
        aux=l->frente;
        while (aux!=NULL&&r==NULL)
        {
            if(memcmp(&e,&aux->e,sizeof(elemento))==0)
            {
                r=aux;
            }
            else
            {
                aux=aux->atras;
            }
        }
    }
    return r;
}

//***************************************************
//Operaci�nes de consulta
//***************************************************
elemento Position(lista *l, posicion p)
{
    if(ValidatePosition(l,p))
    {
        return p->e;
    }
    else
    {
        printf("ERROR: Position(La posicion no es invalida)");
        exit(1);
    }
}

int Size(lista *l)
{
    return l->tam;
}

boolean Empty(lista *l)
{
    return (l->tam > 0) ? FALSE : TRUE;
}

boolean ValidatePosition(lista *l, posicion p)
{
    boolean r = FALSE;
    posicion aux;
    aux = l->frente;
    while (aux!=NULL && !r)
    {
        if (memcmp(&aux, &p, sizeof(posicion)) == 0)
            r = TRUE;
        aux = aux->atras;
    }
    if(memcmp(&l->final, &p, sizeof(posicion)) == 0)
        r = TRUE;
    return r;
}


//***************************************************
//Operaciones de modificaci�n
//***************************************************
void Insert(lista *l, elemento e, posicion p,boolean b)
{
    posicion aux;
    aux = malloc(sizeof(nodo));
    if (aux==NULL)
    {
        printf("ERROR Insert: no se pudo crear un nuevo nodo");
        exit(1);
    }
    aux->e = e;
    if (!ValidatePosition (l,p))
    {
        aux->atras = l->frente;
        aux->adelante = NULL;
        l->frente = aux;
        if (l->tam == 0)
            l->final = aux;
        else
            l->frente->atras->adelante = l->frente;
    }
    else
    {
        if (b)
        {
            aux->adelante = p->adelante;
            p->adelante = aux;
            aux->atras = p;
            if (p==l->frente)
                l->frente = aux;
            else
                aux->adelante->atras = aux;
        }
        else
        {
            aux->adelante = p;
            aux->atras = p->atras;
            p->atras = aux;
            if (p==l->final)
                l->final = aux;
            else
                aux->atras->adelante = aux;
        }
    }
    l->tam++;
}

/**
Solo a�ade en el final un nuevo elemento y un nodo
**/
void Add(lista *l,elemento e)
{
    posicion aux = NULL, aux2;
    aux = malloc(sizeof(nodo));
    if(aux != NULL)
    {
        aux->e = e;
        aux->atras = NULL;
        aux->adelante = NULL;
        if(!Empty(l))
        {
            if(l->final->adelante != NULL){
                l->final->adelante->atras = l->final;
            }
            aux->adelante = l->final;
            l->final = aux;
        }
        else
        {
            l->final = aux;
            l->frente = aux;
        }
        l->tam++;
    }
    else
    {
        printf("\nError no se pudo crear un nuevo nodo");
    }
}

void Remove(lista *l,posicion p)
{
    posicion Pos_adelante, Pos_atras;
    if(!Empty(l))
    {
        if(ValidatePosition(l, p))
        {
            //Si esto ocurre es el nodo del principio
            if(p->adelante == NULL)
                l->frente = l->frente->atras;
            //Si eso ocurre es el nodo del final
            else if(p->atras == NULL)
                l->final = l->final->adelante;
            else
            {
                    Pos_adelante = p->adelante;
                    Pos_atras = p->atras;
                    Pos_adelante->atras = Pos_atras;
                    Pos_atras->adelante = Pos_adelante;
            }
            l->tam--;
            free(p);
        }
    }
    else
    {
        printf("\nEsta vacia");
    }
}

void Replace(lista *l,posicion p, elemento e)
{
    if(!Empty(l))
    {
        if(ValidatePosition(l, p))
        {
            p->e=e;
        }
    }
}
