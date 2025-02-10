#include<bits/stdc++.h>

#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);

const int opposites[6] = {5, 3, 4, 1, 2, 0};
int adjacents[6][4];

int N;
int dices[10'000][6];

void init(){
	for(int face = 0; face < 6; ++face){
		int opposite = opposites[face];

		int cur = 0;
		for(int pip = 0; pip < 6; ++pip){
			if(pip != face && pip != opposite){
				adjacents[face][cur++] = pip;
			}
		}
	}
}

int solution(int at){
	int join = dices[0][at];

	int sum = 0;
	for(int i = 0; i < N; ++i){
		int* dice = dices[i];
		// join의 인덱스를 찾음 => faceAt
		int faceAt = std::find(dice, dice + 6, join) - dice;
		// join의 adjacent 중 가장 큰 값을 더함
		int maxAt = *std::max_element(adjacents[faceAt], adjacents[faceAt] + 4, 
			[&dice](int lhs, int rhs){ 
				return dice[lhs] < dice[rhs];
			}
		);
		sum += dice[maxAt];
		// join을 opposite로 갱신
		join = dice[opposites[faceAt]];
	}
	return sum;
}

int main() {
	FASTIO
	
	init();
	std::cin >> N;
	for(int i = 0; i < N; ++i){
		for(int j = 0; j < 6; ++j){
			std::cin >> dices[i][j];
		}
	}

	int max = solution(0);
	for(int at = 1; at < 6; ++at){
		max = std::max(max, solution(at));
	}
	std::cout << max;
}