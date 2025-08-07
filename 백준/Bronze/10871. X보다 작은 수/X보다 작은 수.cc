#include <iostream>

using namespace std;

int main() {
	int t, X;
	int a;
	cin >> t >> X;
	
	for(int i = 0; i < t; i++) {
		cin >> a;
		if(X > a) cout << a << ' ';
	}
	
	return 0;
}