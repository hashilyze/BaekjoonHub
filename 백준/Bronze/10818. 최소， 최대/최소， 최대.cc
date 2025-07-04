#include <iostream>

using namespace std;

int main() {
	int T;
	
	cin >> T;
	int max = -2147483647, min = -max;

	while(T--) {
        int x;
		cin >> x;
        max = std::max(max, x);
        min = std::min(min, x);
	}
	cout << min << ' ' << max;
	


	return 0;
}
 