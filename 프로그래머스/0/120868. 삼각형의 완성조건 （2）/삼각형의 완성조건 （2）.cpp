#include <bits/stdc++.h>

using namespace std;

int solution(vector<int> sides) {
    std::sort(sides.begin(), sides.end());
    
    int cnt = 0;
    {
        // b = sides[0]; c = sides[1];
        // (a <= c < a + b) -> (c - b < a <= c)
        int lower = sides[1] - sides[0];
        int upper = sides[1];
        cnt += upper - lower;
    }
    {
        // a = sides[0]; b = sides[1];
        // b <= c < a + b
        int lower = sides[1];
        int upper = sides[1] + sides[0];
        cnt += upper - lower - 1;
    }
    return cnt;
}