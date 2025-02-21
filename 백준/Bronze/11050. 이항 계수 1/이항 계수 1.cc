#include <iostream>

// macros
#define FASTIO std::ios_base::sync_with_stdio(false); std::cin.tie(NULL); std::cout.tie(NULL);
// constants
constexpr int MAX_N = 10;
// variabels
int fact[MAX_N + 1];


int main(void){
    FASTIO

    fact[0] = fact[1] = 1;
    for(int i = 2; i <= MAX_N; ++i){
        fact[i] = fact[i - 1] * i;
    }

    int n, k;
    std::cin >> n >> k;
    std::cout << fact[n] / fact[k] / fact[n - k] << '\n';
    
    

    return 0;
}