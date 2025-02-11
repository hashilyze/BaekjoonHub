#include <iostream>
#include <vector>

#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
using ivec = std::vector<int>;
// constants
constexpr int MAX_N = 32'000;
constexpr int MAX_M = 100'000;
// variabls
int N, M;
ivec adj[MAX_N];
bool isvisited[MAX_N] = { 0, };
std::vector<int> result;

void dfs(int u) {
	if (isvisited[u]) return;
	isvisited[u] = true;

	for (int v : adj[u]) {
		dfs(v);
	}
	result.push_back(u);
}

void solution() {
	for (int u = 0; u < N; ++u) {
		dfs(u);
	}
}

int main(void) {
    FASTIO
	std::cin >> N >> M;
	for (int i = 0; i < M; ++i) {
		int u, v;
		std::cin >> u >> v;
		adj[u - 1].push_back(v - 1);
	}
	solution();
	for (int i = N - 1; i >= 0; --i) {
		std::cout << result[i] + 1 << ' ';
	}
}