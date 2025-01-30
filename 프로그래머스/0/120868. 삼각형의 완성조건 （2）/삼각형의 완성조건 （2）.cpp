#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> sides) {
    return 2 * std::min(sides[0], sides[1]) - 1;
}