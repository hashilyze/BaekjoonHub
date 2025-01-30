#include <bits/stdc++.h>

using namespace std;

int solution(int M, int N) {
    if(M == 1 && N == 1) return 0;
    
    int cnt = 1;
    if(M < N){
        if(N % 2) cnt += solution(M, N / 2) + solution(M, N / 2 + 1);
        else cnt += 2 * solution(M, N / 2);
    } else{
        if(M % 2) cnt += solution(M / 2, N) + solution(M / 2 + 1, N);
        else cnt += 2 * solution(M / 2, N);
    }
    return cnt;
}