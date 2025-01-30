#include <bits/stdc++.h>

using namespace std;

int solution(int balls, int share) {
    constexpr int N = 30;
    int comb[N + 1][N + 1] = {0, };
    for(int i = 0; i <= N; ++i) comb[i][0] = 1;
    for(int i = 1; i <= N; ++i){
        for(int j = 1; j <= i; ++j){
            comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
        }
    }
    return comb[balls][share];
}