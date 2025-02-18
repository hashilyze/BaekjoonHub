#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MAX_N = 500;
int N, M;
vector<int> adj[MAX_N];
vector<int> reverseAdj[MAX_N];

// BFS를 사용하여 특정 노드에서 도달할 수 있는 노드 수를 계산
int countNodes(int s, vector<int> adj[]) {
	int cnt = -1;
	vector<bool> isVisited(N, false);
	queue<int> q;
	q.push(s);

	while (!q.empty()) {
		int u = q.front();
		q.pop();

		if (isVisited[u]) continue;
		isVisited[u] = true;
		++cnt;

		for (int v : adj[u]) {
			q.push(v);
		}
	}
	return cnt;
}

// 정확한 순위를 알 수 있는 학생의 수 계산
int solution() {
	int cnt = 0;
	for (int u = 0; u < N; ++u) {
		int cntUpper = countNodes(u, adj);
		int cntLower = countNodes(u, reverseAdj);
		if (cntUpper + cntLower == N - 1) {
			++cnt;
		}
	}
	return cnt;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	// 입력 처리
	cin >> N >> M;

	for (int i = 0; i < N; ++i) {
		adj[i].clear();
		reverseAdj[i].clear();
	}

	for (int i = 0; i < M; ++i) {
		int u, v;
		cin >> u >> v;
		--u, --v;  // 0-based index 변환
		adj[u].push_back(v);
		reverseAdj[v].push_back(u);
	}

	// 출력
	cout << solution() << "\n";
	return 0;
}
