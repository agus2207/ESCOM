#include "Primos.h"
#include "Criba.h"
#include <iostream>
#include <vector>

using namespace std;

Criba::Criba(int a){
    numero = a;
    for (int i = 0; i <= a; i++){
        Primos p(i, true);
        primos.push_back(p);
    }
}
		
void Criba::iniciarcriba(){
	for(int i = 2; i <= numero; i++){
        bool marca = primos[i].obtenerb();
        if(marca == true){
            for(int j = i; i*j <= numero; j++){
                primos[i*j].cambiarbool(false);
            }
        }
    }
}

void Criba::imprimePrimos(){
	for(int i = 2; i <= numero; i++){
        bool primo = primos[i].obtenerb();
        if(primo == true)
            primos[i].imprimir();
    }

}