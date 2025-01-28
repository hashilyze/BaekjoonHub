#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> num_list) {
    std::sort(num_list.begin(), num_list.end());
    vector<int> answer;
    std::copy(num_list.begin(), num_list.begin() + 5, std::back_inserter(answer));
    return answer;
}