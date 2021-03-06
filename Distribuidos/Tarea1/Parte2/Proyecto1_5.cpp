#include <iostream>
using namespace std;

class Fraccion{
    private:
        int numerador;
        int denominador;
    public:
        Fraccion(int = 3, int = 4);
        void inicializaFraccion(int, int);
        void Resultado();
        void minima();
};

Fraccion::Fraccion(int num, int den){
    numerador = num;
    denominador = den;
}

void Fraccion::inicializaFraccion(int num, int den){
    numerador = num;
    denominador = den;  
    return;
}
 
void Fraccion::Resultado(){
    double resultado = static_cast<double>(numerador)/static_cast<double>(denominador);
    cout << "El resultado es igual a: " << resultado << endl;
    return;   
}

void Fraccion::minima(){
    int i = 2;
    while(denominador >= i){
        if(numerador%i == 0 && denominador%i == 0){
            numerador = numerador/i;
            denominador = denominador/i;
        }
        else
            i++;
    }
    cout << "La fraccion minima es "<< numerador <<"/" << denominador << endl;
    return;
}

int main(){
    Fraccion a;
    int num, den;
    cout << "Introduzca numerador y denominador " << endl;
    cin >> num >> den;
    a.inicializaFraccion(num, den);
    a.Resultado();
    a.minima();   
}