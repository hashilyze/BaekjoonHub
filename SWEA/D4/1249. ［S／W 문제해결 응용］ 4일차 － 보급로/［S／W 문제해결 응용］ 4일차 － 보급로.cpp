#include <iostream>
#include <vector>
#include <queue>
#include <functional>
#include <cstring>

#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
class Node {
public:
	int w = 0;
	int y = 0, x = 0;

	Node(){}
	Node(int w, int y, int x) : w(w), y(y), x(x) { }
};
using MaxHeap = std::priority_queue<Node, std::vector<Node>, std::function<bool(Node, Node)>>;
// constants
constexpr int MAX_N = 100;
const int dy[4] = { 0, 0, 1, -1 };
const int dx[4] = { 1, -1, 0, 0 };
// variabls
int T;
int N;
int mat[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N] = { 0, };


int solution() {
	std::memset(isVisited, 0x00, sizeof(isVisited));
	MaxHeap heap([](Node lhs, Node rhs)-> bool { return lhs.w > rhs.w; });
	heap.push(Node());
	while (!heap.empty()) {
		Node u = heap.top(); heap.pop();
		if (isVisited[u.y][u.x]) continue;
		isVisited[u.y][u.x] = true;

		if (u.x == N - 1 && u.y == N - 1) return u.w;

		for (int i = 0; i < 4; ++i) {
			int ny = dy[i] + u.y;
			int nx = dx[i] + u.x;
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				heap.push(Node(u.w + mat[ny][nx], ny, nx));
			}
		}
	}
	return -1;
}

int main(void) {
	FASTIO

	std::cin >> T;
	for (int t = 1; t <= T; ++t) {
		std::cin >> N;
		char digit;
		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < N; ++x) {
				std::cin >> digit;
				mat[y][x] = digit - '0';
			}
		}
		
		int ans = solution();
		std::cout << '#' << t << ' ' << ans << '\n';
	}
}