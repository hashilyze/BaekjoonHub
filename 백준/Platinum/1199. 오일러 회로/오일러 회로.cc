#define _CRT_SECURE_NO_WARNINGS_

#include<iostream>
#include<vector>
#include <array>

constexpr int SIZE = 1000;

int N;
std::vector<std::array<int, 3>> adj[SIZE+1];
int degrees[SIZE + 1];
std::vector<int> path;


void dfs(int u) {
	int size;
	while((size = adj[u].size()) > 0) {
		auto node = adj[u].back();
		if(node[2] == 0) {
			adj[u].pop_back();
			continue;
		}

		--adj[u][size - 1][2];
		--adj[node[0]][node[1]][2];
		dfs(node[0]);
	}
	path.push_back(u);
}

bool solution() {
	for(int deg : degrees) if(deg & 1) return false;

	dfs(0);
	return true;
}

int main(void) {
	std::ios::sync_with_stdio(0); std::cin.tie(0);
	std::cin >> N;
	for(int u = 0; u < N; ++u) {
		for(int v = 0; v < N; ++v) {
			int edge; std::cin >> edge;

			if(u < v) {
				adj[u].push_back({v, (int)adj[v].size(), edge});
				adj[v].push_back({u, (int)adj[u].size()-1, edge});
			}
			degrees[u] += edge;
		}
	}
	if(solution()) {
		for(int u : path) {
			std::cout << u + 1 << ' ';
		}
	} else {
		std::cout << -1;
	}
}