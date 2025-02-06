#include <bits/stdc++.h>
#include <stack>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
// constants
constexpr int MAX_N = 1'000'000;
constexpr int MAX_VALUE = 1'000'000;
// variables
int N;
int arr[MAX_N];
int ans[MAX_N];


int main(void){
    FASTIO

    std::cin >> N;
    for(int i = 0; i < N; ++i) std::cin >> arr[i];

    std::stack<int> stk;
    stk.push(MAX_VALUE + 1);
    for(int i = N - 1; i >= 0; --i){
        while(stk.top() <= arr[i]) stk.pop();

        ans[i] = stk.top();
        if(ans[i] == MAX_VALUE + 1) ans[i] = -1;

        stk.push(arr[i]);
    }
    for(int i = 0; i < N; ++i) std::cout << ans[i] << ' ';

    return 0;
}