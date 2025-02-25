#include <bits/stdc++.h>
#include <unordered_map>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
//types
using ll = long long;
// constnts
// variables
int N, K;
std::unordered_map<ll, ll> map;


int main() {
	FASTIO;

	std::cin >> N >> K;

	ll prev = 0, cur;
	ll cnt = 0;

	++map[0];
	for (int i = 1; i <= N; ++i) {
		std::cin >> cur;
		cur += prev;

		cnt += map[cur - K];
		++map[cur];

		prev = cur;
	}
	std::cout << cnt;

	return 0;
}