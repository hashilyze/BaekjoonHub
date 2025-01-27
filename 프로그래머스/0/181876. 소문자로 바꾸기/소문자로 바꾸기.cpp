#include <bits/stdc++.h>

using namespace std;

string solution(string str) {
    std::transform(str.begin(), str.end(), str.begin(), ::tolower);
    return str;
}