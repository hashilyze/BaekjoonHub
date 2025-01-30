#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<string> keyinput, vector<int> board) {
    int wLimit = board[0] / 2;
    int hLimit = board[1] / 2;
    
    vector<int> ans = {0, 0};
    for(const string &key : keyinput){
        if(key == "up"){
            ans[1] = std::min(ans[1] + 1, +hLimit);
        } else if(key =="down"){
            ans[1] = std::max(ans[1] - 1, -hLimit);
        } else if(key == "left"){
            ans[0] = std::max(ans[0] - 1, -wLimit);
        } else { // key == "right"
            ans[0] = std::min(ans[0] + 1, +wLimit);
        } 
    }
    return ans;
}