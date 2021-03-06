#include <iostream>
using namespace std;
int main( ){
    int numero;
    double r, t=0.0;  
    cout << "introduzca el numero al que le desea calcular la raiz cuadrada.\n";
    cin >> numero;
    r = static_cast<double>(numero);
    while(t != r){
        t = r;
        r = 1.0/2.0*((static_cast<double>(numero)/r)+r);
    }
    cout<<"el resultado es; "<<r<<"\n";
    return 0;
}