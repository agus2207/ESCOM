#include <stdio.h>
#include <stdlib.h>

typedef struct nodo{
	struct nodo *siguiente;
	char caracter;
}nodo;

nodo *primero= NULL;

void push (char caracter2){
	nodo *nuevo= (nodo*)malloc(sizeof(nodo));
	nuevo->caracter= caracter2;
	nuevo->siguiente= primero;
	primero= nuevo;
}

void imprimir (){
	nodo *actual= (nodo*)malloc(sizeof(nodo));
	actual= primero;
	if (actual!=NULL){
		while (actual!=NULL){
			printf ("%c", actual->caracter);
			actual= actual->siguiente;
		}
	}else{
		printf ("La Pila se encuentra vacia");
	}
}

void imprimirgrafico (){
	nodo *actual= (nodo*)malloc(sizeof(nodo));
	actual= primero;
	if (actual!=NULL){
		while (actual!=NULL){
			printf ("%c -> ", actual->caracter);
			actual= actual->siguiente;
		}
		printf ("NULL\n");
	}else{
		printf ("La Pila se encuentra vacia");
	}
}

void pop(char caracter2){
	nodo *actual= (nodo*)malloc(sizeof(nodo));
	nodo *anterior= (nodo*)malloc(sizeof(nodo));
	int encontrado=0;
	actual= primero;
	anterior= NULL;
	if (actual!=NULL){
		while (actual!=NULL && encontrado!=1){
			if (caracter2== actual->caracter){
				encontrado=1;
				if (actual==primero){
					primero= primero->siguiente;
				}
			}
			anterior= actual;
			actual= actual->siguiente;
		}
		if (encontrado==1){
			free (anterior);
		}
	}else{
		printf ("La Pila ya se encuentra vacia\n");
	}
}

int vacia (){
	nodo *actual= (nodo*)malloc(sizeof(nodo));
	actual= primero;
	if (actual->caracter=='z'){
		return 1;
	}else{
	return 0;
	}
}




