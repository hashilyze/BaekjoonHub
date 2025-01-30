#include <bits/stdc++.h>

using namespace std;

vector<int> solution(int num, int total) {
    int beg = -100;
    int end = -100 + num;
    
    int sum = 0;
    for(int i = beg; i < end; ++i) sum += i;
    while(sum != total){
        sum -= beg++;
        sum += end++;
    }
    std::vector<int> answer;
    for(int i = beg; i < end; ++i) answer.push_back(i);
    return answer;
}