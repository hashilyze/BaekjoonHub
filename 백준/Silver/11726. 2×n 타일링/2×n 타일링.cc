#include <bits/stdc++.h>

// Macros
#define FASTIO std::ios::sync_with_stdio(false); std::cin.tie(0);
// Types
using pii = std::pair<int, int>;
using ivec = std::vector<int>;
using ll = long long;
// Constant
// Variables
int N;
int dp[1'001];

int main(void) {
	FASTIO

	std::cin >> N;
	dp[1] = 1; dp[2] = 2;
	for(int i = 3; i <= N; ++i) {
		dp[i] = (dp[i - 1] + dp[i - 2]) % 10'007;
	}
	std::cout << dp[N];

	return 0;
}