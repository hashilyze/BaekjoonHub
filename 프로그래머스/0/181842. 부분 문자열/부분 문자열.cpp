#include <bits/stdc++.h>

using namespace std;

int solution(string str1, string str2) {
    return str2.find(str1) != std::string::npos ? 1 : 0;
}