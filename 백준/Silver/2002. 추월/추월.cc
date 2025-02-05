#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
// constants
// variables
int N;
std::unordered_map<std::string, int> ranks;
std::stack<int> stk;


int main(void){
    std::cin >> N;
    for(int i = 0; i < N; ++i){
        std::string car; std::cin >> car;
        ranks[car] = i;
    }

    stk.push(-1);
    for(int i = 0; i < N; ++i){
        std::string car; std::cin >> car;
        int rank = ranks[car];
        
        while(stk.top() > rank) stk.pop();
        stk.push(rank);
    }
    std::cout << N - stk.size() + 1;
    
    return 0;
}