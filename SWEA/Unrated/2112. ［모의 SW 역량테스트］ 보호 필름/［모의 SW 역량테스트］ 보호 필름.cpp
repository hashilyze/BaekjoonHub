#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
//types
// constnts
constexpr int A = 0x00;
constexpr int B = (0x01LL << 32) - 1;
// variables
int D, W, K;
int cells[13];
int min;

bool isPassed() {
	for (int x = 0; x < W; ++x) {
		bool pass = false;
		int prev = -1, len = 0;
		for (int y = 0; y < D; ++y) {
			int cur = (cells[y] >> x) & 1;
			if (prev == cur) {
				++len;
			} else {
				prev = cur;
				len = 1;
			}

			if (len >= K) {
				pass = true;
				break;
			}
		}
		if (!pass) return false;
	}
	return true;
}

void eachSubset(int idx, int cnt) {
	if (min < cnt) return; // 가지치기: 최솟값보다 더 투여할 경우 중단

	if (idx == D) {
		if (isPassed()) min = std::min(min, cnt);
		return;
	}

	int cached = cells[idx];
	eachSubset(idx + 1, cnt); // 투여 X
	cells[idx] = A;
	eachSubset(idx + 1, cnt + 1); // 약물 A 투여
	cells[idx] = B;
	eachSubset(idx + 1, cnt + 1); // 약물 B 투여
	cells[idx] = cached; // 복원
}

int solution() {
	min = 2147483647;
	eachSubset(0, 0);
	return min;
}

int main() {
	FASTIO;
	
	int T; std::cin >> T;
	for (int t = 1; t <= T; ++t) {
		std::cin >> D;
		std::cin >> W;
		std::cin >> K;

		for (int y = 0; y < D; ++y) {
			int bits = 0;
			for (int x = 0, b; x < W; ++x) {
				std::cin >> b;
				bits = (bits << 1) | b;
			}
			cells[y] = bits;
		}
		std::cout << '#' << t << ' ' << solution() << '\n';
	}
}