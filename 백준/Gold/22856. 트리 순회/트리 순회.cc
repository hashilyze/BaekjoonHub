#include <iostream>
#include <algorithm>
#include <cstring>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constnts
constexpr int MAX_N = 100'000;
constexpr int ROOT = 1;
// variables
int N;
int parents[MAX_N + 1];
int lefts[MAX_N + 1];
int rights[MAX_N + 1];

int cnt = 0;

int getMinNode(int node) {
	while (lefts[node] > 0) {
		node = lefts[node];
		++cnt;
	}
	return node;
}

int getNextNode(int node) { 
	if (rights[node] > 0) { // next 노드가 오른쪽 자식 중에 존재
		++cnt;
		return getMinNode(rights[node]);
	} else { // next 노드가 조상 중에 존재(할 것으로 추정)
		int tmp = 1;
		int parent = parents[node];
		while (parent > 0 && node != lefts[parent]) {
			node = parent;
			parent = parents[parent];
			++tmp;
		}
		if (parent == 0) {
			return -1;
		}
		cnt += tmp;
		return parent;
	}
}

int solution() {
	int node = getMinNode(ROOT);
	while ((node = getNextNode(node)) > 0) {
		continue;
	}
	return cnt;
}

int main() {
	FASTIO
		;
	std::cin >> N;
	for (int i = 0; i < N; ++i) {
		int c, l, r;
		std::cin >> c >> l >> r;

		lefts[c] = l;
		if (l > 0) parents[l] = c;

		rights[c] = r;
		if (r > 0) parents[r] = c;
	}
	std::cout << solution();

	return 0;
}