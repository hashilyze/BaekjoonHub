#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> numlist, int n) {
    std::sort(numlist.begin(), numlist.end(), [n](int lhs, int rhs){
        if(std::abs(lhs - n) == std::abs(rhs - n)) {
            return lhs > rhs;
        }
        return std::abs(lhs - n) < std::abs(rhs - n);
    });
    return numlist;
}