#include<bits/stdc++.h>

#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);

// constants
constexpr int MAX_N	 = 32;
// variables
int N;
bool isBlocked[MAX_N][MAX_N];
long dp[MAX_N][MAX_N][3]; // <가로, 세로, 대각선>


long solution(){
	dp[0][1][0] = 1;
	for(int x = 2; x < N; ++x){
		if(isBlocked[0][x]) break;
		dp[0][x][0] += dp[0][x - 1][0];
	}

	for(int y = 1; y < N; ++y){
		for(int x = 2; x < N; ++x){
			if(isBlocked[y][x]) continue;

			if(!isBlocked[y][x - 1]) // 가로 파이프
				dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][2];
			if(!isBlocked[y - 1][x]) // 세로 파이프
				dp[y][x][1] += dp[y - 1][x][1] + dp[y - 1][x][2];
			if(!isBlocked[y - 1][x] && !isBlocked[y][x - 1] && !isBlocked[y - 1][x - 1]) // 대각선 파이프
				dp[y][x][2] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
		}
	}
	return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
}

int main() {
	FASTIO

	std::cin >> N;
	for(int y = 0; y < N; ++y){
		for(int x = 0; x < N; ++x){
			std::cin >> isBlocked[y][x];
		}
	}
	std::cout << solution();	

	return 0;
}