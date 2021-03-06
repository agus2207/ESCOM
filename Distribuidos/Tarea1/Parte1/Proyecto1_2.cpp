#include <iostream>
using namespace std;
int main( ){
    int tiempo;
    double h, g=9.8;  
    cout << "introduzca el tiempo en segundos en la altura maxima.\n";
    cin >> tiempo;
    h = 1.0/2.0*(g*static_cast<double>(tiempo*tiempo));
    cout<<"el resultado es; "<<h<<"\n";
    return 0;
}