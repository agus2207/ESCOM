#include <iostream>
#include <iomanip>

using namespace std;
int main (){
	int n;
	double p=1;
	cin>>n;
	for (int i=0; i<n; i++){
		p=2*p;
	}
	cout<<setprecision(80)<<p;
	return 0;
}
