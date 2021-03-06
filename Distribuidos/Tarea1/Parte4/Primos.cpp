#include "Primos.h"
#include <iostream>

using namespace std;

Primos::Primos(int xx, bool yy) : x(xx), b(yy){ }

double Primos::obtenerx(){
	return x;
}

double Primos::obtenerb(){
	return b;
}

void Primos:: imprimir(){
	cout << "Primo encontrado: " << x <<"\n";
}

void Primos::cambiarbool(bool bb){
	b = bb;
}