// Beakjoon 1850 - 최대공약수
// https://www.acmicpc.net/problem/1850

#include <iostream>
#include <string>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
using ll = long long;
// constants
// variables


ll gcd(ll a, ll b){
    if(a < b) std::swap(a, b);
    while(b != 0){
        ll tmp = b;
        b = a % b;
        a = tmp;
    }
    return a;
}

int main(void){
    FASTIO
    
    ll A, B;
    std::cin >> A >> B;
    std::cout << std::string(gcd(A, B), '1');

    return 0;
}