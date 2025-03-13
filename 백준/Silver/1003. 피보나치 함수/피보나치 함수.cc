#include <bits/stdc++.h>

// Macros
#define FASTIO std::ios::sync_with_stdio(false); std::cin.tie(0);
// Types
// Constants
// Variables
int T, N;
int fib[41];


void init() {
	fib[0] = 0;
	fib[1] = fib[2] = 1;
	for(int i = 3; i <= 40; ++i) {
		fib[i] = fib[i - 1] + fib[i - 2];
	}
}

int main(void) {
	FASTIO 
	init();

	//std::ifstream fin("in.txt"); 
	//std::ofstream fout("out.txt");
	//std::cin.rdbuf(fin.rdbuf());
	//std::cout.rdbuf(fout.rdbuf());

	std::cin >> T;
	while(T--) {
		std::cin >> N;

		if(N == 0) {
			std::cout << "1 0\n";
		} else if(N == 1) {
			std::cout << "0 1\n";
		} else {
			std::cout << fib[N - 1] << ' ' << fib[N] << '\n';
		}
	}

	return 0;
}