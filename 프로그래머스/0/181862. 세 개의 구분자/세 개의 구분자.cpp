#include <bits/stdc++.h>

using namespace std;

vector<string> solution(string myStr) {
    for(char &ch : myStr) {
        if(ch == 'a' || ch == 'b' || ch == 'c') ch = ' ';
    }
    
    std::vector<std::string> ans;
    std::istringstream iss(myStr);
    std::string word;
    while(iss >> word) ans.push_back(word);
    
    if(ans.size() == 0) ans.push_back("EMPTY");
    return ans;
}