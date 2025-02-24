#include <bits/stdc++.h>

int N, M;
int arr[100'000];

int main(void) {
	std::ios::sync_with_stdio(0); std::cin.tie(0);

	std::cin >> N >> M;
	for (int i = 0; i < N; ++i) std::cin >> arr[i];
	std::sort(arr, arr + N);
	
	int min = 2'000'000'000 + 1;
	int lcur = 0, rcur = 1;
	while (lcur < N - 1) {
		if (arr[rcur] - arr[lcur] >= M) {
			min = std::min(min, arr[rcur] - arr[lcur]);
			++lcur;
		} else{
			++rcur;
		}
	}
	std::cout << min;
	
	return 0;
}