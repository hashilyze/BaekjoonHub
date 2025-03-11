#include <bits/stdc++.h>

// Macros
#define FASTIO std::ios::sync_with_stdio(false); std::cin.tie(0);
// Types
// Constants
// Variables
int N;
int dp[1'000'001];

int main(void) {
	FASTIO

	//std::ifstream fin("in.txt");
	//std::ofstream fout("out.txt");
	//std::cin.rdbuf(fin.rdbuf());
	//std::cout.rdbuf(fout.rdbuf());

	std::cin >> N;
	dp[1] = 0;
	dp[2] = 1;
	dp[3] = 1;
	for(int i = 4; i <= N; ++i) {
		dp[i] = dp[i - 1] + 1;
		if(i % 2 == 0) dp[i] = std::min(dp[i], dp[i / 2] + 1);
		if(i % 3 == 0) dp[i] = std::min(dp[i], dp[i / 3] + 1);
	}
	std::cout << dp[N];

	return 0;
}