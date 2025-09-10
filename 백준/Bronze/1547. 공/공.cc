#include <iostream>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int m;
    if (!(std::cin >> m)) return 0;

    int ball = 1; // 처음에 공은 1번 컵 아래

    for (int i = 0; i < m; ++i) {
        int a, b;
        std::cin >> a >> b;

        // 교환에 공이 포함되면 위치만 갱신
        if (a == ball) {
            ball = b;
        } else if (b == ball) {
            ball = a;
        }
        // 공이 없는 컵끼리 바뀌면 아무 일도 없음
    }

    std::cout << ball << '\n';
    return 0;
}
