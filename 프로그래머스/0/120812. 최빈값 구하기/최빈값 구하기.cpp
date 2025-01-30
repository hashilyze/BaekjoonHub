#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> array) {
    constexpr int N = 1000;
    int freq[N + 1] = {0, };
    for(int e : array) ++freq[e];
    
    auto max = std::max_element(std::begin(freq), std::end(freq));
    int cnt = std::count(std::begin(freq), std::end(freq), *max);
    if(cnt > 1) return -1;
    return max - freq;
}