#include <bits/stdc++.h>

int N;

int main () {
    std::scanf("%d", &N);
    for(int i = 1; i <= N; ++i){
        std::cout << std::string(N - i, ' ') << std::string(i, '*') << '\n';
    }
}
