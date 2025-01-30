#include <bits/stdc++.h>

using namespace std;

int solution(string A, string B) {
    int shift = (B + B).find(A);
    return shift == std::string::npos ? -1 : shift;
}