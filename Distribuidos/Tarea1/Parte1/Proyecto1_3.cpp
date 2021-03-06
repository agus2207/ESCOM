#include <iostream>
using namespace std;
int main( ){
    int tiempo, horas, minutos;
    cout << "introduzca el tiempo en segundos.\n";
    cin >> tiempo;
    horas = tiempo/3600;
    minutos = tiempo%3600;
    tiempo = minutos%60;
    minutos = minutos/60;
    cout<<"Horas:"<<horas<<" Minutos:"<<minutos<<" Segundos:"<<tiempo<<"\n";
    return 0;
}