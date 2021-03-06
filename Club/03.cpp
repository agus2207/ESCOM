#include <iostream>

using namespace std;

int main (){
	string cadena;
	while(getline(cin,cadena)){
		//cout<<cadena<<'\n';
		//cout<<cadena[0];
		for(int i = cadena.length()-1; i >= 0; i--){
			cout<<cadena[i];
		}
		cout<<'\n';
	}
	return 0;
}