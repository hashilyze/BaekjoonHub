#include <bits/stdc++.h>

using namespace std;

string solution(vector<string> id_pw, vector<vector<string>> db) {
    std::unordered_map<std::string, std::string> map;
    for(const std::vector<std::string> &record : db){
        map[record[0]] = record[1];
    }
    auto it = map.find(id_pw[0]);
    if(it == map.end()) return "fail";
    else if(it->second != id_pw[1]) return "wrong pw";
    else return "login";
}