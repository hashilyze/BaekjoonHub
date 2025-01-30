#include <bits/stdc++.h>

using namespace std;

bool evaluate(std::string & str){
    std::istringstream iss(str);
    
    int lhs, rhs, ret;
    char op, asg;
    
    iss >> lhs >> op >> rhs >> asg >> ret;
    switch(op){
        case '+': return lhs + rhs == ret;
        case '-': return lhs - rhs == ret;
    }
    return -1;
}

vector<string> solution(vector<string> quiz) {
    vector<string> answer;
    for(std::string &q : quiz){
        answer.push_back(evaluate(q) ? "O" : "X");
    }
    return answer;
}