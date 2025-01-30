#include <bits/stdc++.h>

using ll = long long;

int N;
std::unordered_map<ll, int> map;

int main(void){
    std::ios::sync_with_stdio(0); std::cin.tie(0);

    std::cin >> N;
    while(N--){
        ll x;
        std::cin >> x;
        map[x] += 1;
    }
    
    ll max_key = 0;
    int max_value = 0;
    for(auto entry : map){
        if(max_value < entry.second || (max_value == entry.second && entry.first < max_key)){
            std::tie(max_key, max_value) = entry;
        }
    }
    std::cout << max_key;
    
    return 0;
}