#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constnts
constexpr int SIZE = 49;
constexpr int PICK = 6;
// variables
int N;
int A[SIZE];
std::vector<int> comb;

void nextCombination(int idx, int left, std::vector<int>& comb) {
	if (left == 0) {
		for (int e : comb) std::cout << e << ' ';
		std::cout << '\n';
		return;
	}

	for (int i = idx; i < N - left + 1; ++i) {
		comb.push_back(A[i]);
		nextCombination(i + 1, left - 1, comb);
		comb.pop_back();
	}
}

int main() {
	FASTIO;
	
	while (true) {
		std::cin >> N;
		if (N == 0) break;
		for (int i = 0; i < N; ++i) std::cin >> A[i];
		std::sort(A, A + N);
		nextCombination(0, PICK, comb);
		std::cout << '\n';
	}

	return 0;
}