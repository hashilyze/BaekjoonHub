#include <bits/stdc++.h>

using namespace std;

vector<string> solution(string my_str, int n) {
    vector<string> answer;
    answer.reserve(my_str.size() / n + 1);
    for(int i = 0; i < my_str.size(); i += n){
        answer.push_back(my_str.substr(i, n));
    }
    return answer;
}