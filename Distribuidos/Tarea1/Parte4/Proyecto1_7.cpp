#include "Primos.h"
#include "Criba.h"
#include <iostream>
#include <vector>

using namespace std;

int main(){
    int numero;
    cout << "Ingrese un numero" << endl;
    cin >> numero;
    Criba c(numero);
    c.iniciarcriba();
    c.imprimePrimos();
}