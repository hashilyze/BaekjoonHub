#include <bits/stdc++.h>

using namespace std;

bool isValid(int n){
    if(n % 3 == 0) return false;
    while(n > 0){
        if(n % 10 == 3) return false;
        n /= 10;
    }
    return true;
}

int solution(int n) {
    int x = 0;
    while(n-- > 0){
        while(!isValid(++x)) continue;
    }
    return x;
}