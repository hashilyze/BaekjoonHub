#include <bits/stdc++.h>

using namespace std;

int solution(string my_string, string is_suffix) {
    return std::regex_match(my_string, std::regex(".*" + is_suffix)) ? 1 : 0;
}