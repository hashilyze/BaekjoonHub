#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> solution(int n) {
    vector<vector<int>> answer;
    answer.reserve(n);
    for(int i = 0; i < n; ++i){
        std::vector<int> line(n, 0);
        line[i] = 1;
        answer.push_back(line);
    }
    return answer;
}