#include <iostream>

using namespace std;

int main (){
	
	int k, p, q, x, y;
	while (cin >>k && k != 0){
		cin >> p >> q;
		for (int i = 0; i < k; i++){
			cin >> x >> y;
			if (x == p || y == q)
				cout << "divisa \n";
			else if (x < p && y < q)
				cout << "SO \n";
			else if (x > p && y > q)
				cout << "NE \n";
			else if (x < p && y > q)
				cout << "NO \n";
			else if (x > p && y < q)
				cout << "SE \n";
		}
	}
	return 0;
}