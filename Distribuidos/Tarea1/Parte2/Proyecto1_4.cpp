#include <iostream>

using namespace std;

class Temperatura{
    private:
        double Kelvin;
    public:
        Temperatura(int k = 3000);
        void setTempKelvin (int);
        void setTempFahrenheit(int);
        void setTempCelsius(int);
};

Temperatura::Temperatura(int k){
    Kelvin = static_cast<double>(k);
}
 
void Temperatura::setTempKelvin(int k){
    Kelvin = static_cast<double>(k);
    cout << "Temperatura en kelvin: " << Kelvin << endl;
    return;   
}

void Temperatura::setTempCelsius(int c){
    Kelvin = static_cast<double>(c) + 273.15;
    cout << c << "°C en kelvin: " <<Kelvin << endl;
    return;
}

void Temperatura::setTempFahrenheit(int f){
    Kelvin = (static_cast<double>(f)*(5.0/9.0))+273.15;
    cout << f << "°F en kelvin: " <<Kelvin << endl;
    return;
}

int main(){
    Temperatura a;
    a.setTempKelvin(273);
    a.setTempCelsius(0);
    a.setTempFahrenheit(32);
}