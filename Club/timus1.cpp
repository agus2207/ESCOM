#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int main (){
	vector<int> num(3);
	int ans = 101;
	cin >> num[0] >> num[1] >> num[2];
	do{
		ans = min(ans, num[0]*num[1]*num[2]);
		ans = min(ans, num[0]*num[1]+num[2]);
		ans = min(ans, num[0]*num[1]-num[2]);
		ans = min(ans, num[0]+num[1]+num[2]);
		ans = min(ans, num[0]+num[1]*num[2]);
		ans = min(ans, num[0]+num[1]-num[2]);
		ans = min(ans, num[0]-num[1]-num[2]);
		ans = min(ans, num[0]-num[1]*num[2]);
		ans = min(ans, num[0]-num[1]+num[2]);

	}while (next_permutation(num.begin(), num.end()));
	cout << ans;
}