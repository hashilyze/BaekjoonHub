#include <iostream>
using namespace std;
 
int main(void) {
	int val, min = 101, sum = 0;
 
	for (int i = 0; i < 7; i++) {
		cin >> val;
		if (val % 2 == 1) {
			sum += val;
			if (val < min) min = val;
		}
	}
	if (sum == 0) cout << -1;
	else cout << sum << '\n' << min;
 
	return 0;
}