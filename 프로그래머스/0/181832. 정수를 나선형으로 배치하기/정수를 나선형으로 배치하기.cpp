#include <bits/stdc++.h>

using namespace std;

const int dx[] = {1, 0, -1, 0};
const int dy[] = {0, 1, 0, -1};

bool isValid(int y, int x, int n){
    return 0 <= y && y < n && 0 <= x && x < n;
}

vector<vector<int>> solution(int n) {
    vector<vector<int>> ans(n, std::vector<int>(n, 0));
    
    int v = 1;
    int dir = 0;
    int y = 0, x = 0;
    while(1){
        ans[y][x] = v++;
        if(v > n * n) break;
        
        int ny, nx;
        while(!isValid(ny = y + dy[dir], nx = x + dx[dir], n) || ans[ny][nx] != 0){
            dir = (dir + 1) % 4;
        }
        y = ny;
        x = nx;
    }
    return ans;
}