#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
//types
using ll = long long;
// constnts
// variables
int N, K;
int A[200'000];
std::unordered_map<ll, ll> map;


int main() {
	FASTIO;

	std::cin >> N >> K;

	ll cnt = 0;
	++map[0];
	for (int i = 1; i <= N; ++i) {
		std::cin >> A[i];
		A[i] += A[i - 1];

		cnt += map[A[i] - K];
		++map[A[i]];
	}
	std::cout << cnt;

	return 0;
}