#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int main(){
	int T;
	vector<int> num(3);
	cin >> T;
	for (int i = 0; i < T; i++){
		cin >> num[0] >> num[1] >> num[2];
		sort(num.begin(), num.end());
		cout <<"Case "<<i+1<<": "<<num[1]<<"\n";
	}
	return 0;
}
