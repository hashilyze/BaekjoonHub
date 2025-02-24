#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constnts
// variables
int N, M;
int A[10'000];

int main() {
	FASTIO;
	std::cin >> N >> M;
	int cnt = 0;
	for (int lcur = 0, rcur = 0; rcur < N; ++rcur) {
		std::cin >> A[rcur];
		M -= A[rcur];
		while (M < 0) M += A[lcur++];
		if (M == 0) ++cnt;
	}
	std::cout << cnt;

	return 0;
}