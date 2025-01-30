#include <bits/stdc++.h>

using ll = long long;

int N;
std::map<ll, int> map;

int main(void){
    std::ios::sync_with_stdio(0); std::cin.tie(0);

    std::cin >> N;
    while(N--){
        ll x;
        std::cin >> x;
        map[x] += 1;
    }
    
    ll max = 0;
    for(auto entry : map){
        if(map[max] < entry.second){
            max = entry.first;
        }
    }
    std::cout << max;
    
    return 0;
}