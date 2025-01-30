#include <bits/stdc++.h>

using namespace std;

int solution(string A, string B) {
    string dblA = A + A;
    int shift = dblA.rfind(B);
    if(shift == std::string::npos) return -1;
    return A.size() - shift;
}