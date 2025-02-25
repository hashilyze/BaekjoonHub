#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
//types
using ll = long long;
// constnts
// variables
int N;


int main() {
	FASTIO;

	std::cin >> N;

	int cnt = 0;

	int M = N;
	int nextL = 1, nextR = 1;
	for (int lcur = 1, rcur = 1; rcur <= N; ++rcur) {
		M -= nextR++;
		while (M < 0) M += nextL++;
		if (M == 0) ++cnt;
	}
	std::cout << cnt;

	return 0;
}