#include <bits/stdc++.h>

using namespace std;

string solution(vector<string> my_strings, vector<vector<int>> parts) {
    string answer = "";
    for(int i = 0, n = my_strings.size(); i < n; ++i){
        const std::string &str = my_strings[i];
        int s = parts[i][0], e = parts[i][1] + 1;
        answer += str.substr(s, e - s);
    }
    return answer;
}