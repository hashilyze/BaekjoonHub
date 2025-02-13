#include <iostream>
#include <algorithm>

#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
// constants
constexpr int MAX_N = 1'000'000;
// variabls
int N;
int perm[MAX_N];
int arr[MAX_N];

int solution() {
	int size = 0;
	for (int i = 0; i < N; ++i) {
		int* at = std::lower_bound(arr, arr + size, perm[i]);
		if (at - arr == size) ++size;
		*at = perm[i];
	}
	return size;
}

int main(void) {
	FASTIO
		;
	std::cin >> N;
	for (int i = 0; i < N; ++i) std::cin >> perm[i];
	std::cout << solution();
}