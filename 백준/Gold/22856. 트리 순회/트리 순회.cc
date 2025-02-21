#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constnts
constexpr int MAX_N = 100'000;
constexpr int ROOT = 1;
// variables
int N;
int parents[MAX_N + 1];
int lefts[MAX_N + 1];
int rights[MAX_N + 1];


int solution() {
	int noback = 0;
	int node = ROOT;
	while (rights[node] > 0) {
		node = rights[node];
		++noback;
	}
	return 2 * (N - 1) - noback;
}


int main() {
	FASTIO
		
	std::cin >> N;
	for (int i = 0; i < N; ++i) {
		int c, l, r;
		std::cin >> c >> l >> r;

		lefts[c] = l;
		if (l > 0) parents[l] = c;

		rights[c] = r;
		if (r > 0) parents[r] = c;
	}
	std::cout << solution();

	return 0;
}