#include <bits/stdc++.h>

using namespace std;

int solution(vector<vector<int>> dots) {
    int minX = 2147483647, maxX = -2147483648;
    int minY = 2147483647, maxY = -2147483648;
    
    for(const std::vector<int> &dot : dots){
        minX = std::min(minX, dot[0]);
        maxX = std::max(maxX, dot[0]);
        minY = std::min(minY, dot[1]);
        maxY = std::max(maxY, dot[1]);
    }
    
    return (maxY - minY) * (maxX - minX);
}