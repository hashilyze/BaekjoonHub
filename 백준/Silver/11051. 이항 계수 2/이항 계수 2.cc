#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constants
constexpr int MAX_N = 1'000;
constexpr int MOD = 10'007;
// variables
int N, K;
int dp[MAX_N + 1][MAX_N + 1];


int main () {
    FASTIO
    std::cin >> N >> K;

    for(int n = 0; n <= N; ++n) dp[n][0] = dp[n][n] = 1;
    for(int n = 2; n <= N; ++n){
        for(int r = 1; r < n; ++r){
            dp[n][r] = (dp[n - 1][r] + dp[n - 1][r - 1]) % MOD;
        }
    }
    std::cout << dp[N][K];
    
}
