#include <iostream>
#include <fstream>
#include <tuple>

const char STAR = '*';
const char EMPTY = ' ';
const int MAX_N = 6561; // 3^8
const std::pair<int, int> MULTIPLIES[] = {
    {0, 0}, {0, 1}, {0, 2},
    {1, 0},         {1, 2},
    {2, 0}, {2, 1}, {2, 2},
};
const std::pair<int, int> DELTAS[] = {
    {0, 0}, {0, 1}, {0, 2},
    {1, 0},         {1, 2},
    {2, 0}, {2, 1}, {2, 2},
};

char Board[MAX_N][MAX_N] = { 0, };


void DrawEmpty(int y, int x, int size){
    for(int dy = 0; dy < size; ++dy){
        for(int dx = 0; dx < size; ++dx){
            Board[y + dy][x + dx] = EMPTY;
        }
    }
}

void DrawSquare(int y, int x, int size){
    if(size == 3) {
        for(auto delta : DELTAS){
            Board[y + delta.first][x + delta.second] = STAR;
        }
        DrawEmpty(y + 1, x + 1, 1);
        return;
    }

    int subSize = size / 3;
    for(auto multiply : MULTIPLIES){
        DrawSquare(y + subSize * multiply.first, x + subSize * multiply.second, subSize);
    }
    DrawEmpty(y + subSize, x + subSize, subSize);
}


int main(void){
    std::ios_base::sync_with_stdio(false);  
    std::cin.tie(NULL);
#ifndef ONLINE_JUDGE
    std::ifstream fin("sol.inp");
    std::ofstream fout("sol.out");
    
    std::istream& in = fin;
    std::ostream& out = fout;
#else 
    std::istream& in = std::cin;
    std::ostream& out = std::cout;
#endif
    int n;
    in >> n;
    DrawSquare(0, 0, n);
    
    for(int y = 0; y < n; ++y){
        for(int x = 0; x < n; ++x){
            out << Board[y][x];
        }
        out << '\n';
    }

    return 0;

}