#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> common) {
    int x0 = common[common.size() - 3];
    int x1 = common[common.size() - 2];
    int x2 = common[common.size() - 1];
    
    if(x1 - x0 == x2 - x1){
        return x2 + (x2 - x1);
    } else{
        return x2 * (x2 / x1);
    }
}