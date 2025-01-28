#include <bits/stdc++.h>

using namespace std;

int solution(vector<string> order) {    
    int ans = 0;
    for(const std::string &str : order) {
        if(str.find("cafelatte") != std::string::npos) ans += 5000;
        else ans += 4500;
    }
    return ans;
}