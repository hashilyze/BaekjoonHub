#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// constnts
constexpr int ASCII = 128;
constexpr int VOWEL = 1;
constexpr int CONSONAT = 2;
// variables
int L, C;
int counts[ASCII];
std::string buffer;
int freqs[ASCII];


bool isValid() {
	int vowel = freqs['a'] + freqs['e'] + freqs['i'] + freqs['o'] + freqs['u'];
	int consonat = L - vowel;

	return vowel >= 1 && consonat >= 2;
}

void nextSubset(int ch, int left) {
	if (left == 0) {
		if (isValid()) {
			std::cout << buffer << '\n';
		}
		return;
	}
	if (ch > 'z') return;

	if (counts[ch] > 0) {
		--counts[ch];
		++freqs[ch];
		buffer.push_back(ch);
		nextSubset(ch, left - 1);
		buffer.pop_back();
		--freqs[ch];
		++counts[ch];
	}
	nextSubset(ch + 1, left);
}


int main() {
	FASTIO;
	
	std::cin >> L >> C;
	for (int i = 0; i < C; ++i) {
		char ch; std::cin >> ch;
		++counts[ch];
	}
	nextSubset('a', L);

	return 0;
}