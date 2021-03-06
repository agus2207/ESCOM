#include <iostream>

using namespace std;

int main (){
	string cadena;
	while(getline(cin,cadena)){
		for(int i = 0; i < cadena.length(); i++){
			char mayus = toupper(cadena[i]);
			cout<<mayus;
		}
		cout<<'\n';
		for(int i = 0; i < cadena.length(); i++){
			char minus = tolower(cadena[i]);
			cout<<minus;
		}
		cout<<'\n';
	}
}