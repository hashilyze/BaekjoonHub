#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
// types
// constants
constexpr int NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;
constexpr int MAX_SIZE = 100;
// variables
int W, H, N;
int locations[MAX_SIZE + 1];

int flatLocation(int dir, int dist){
    switch (dir) {
    case NORTH: return dist;
    case EAST: return W + dist;
    case SOUTH: return (W << 1) + H - dist;
    case WEST: return ((W + H) << 1) - dist;
    }
    return -1;
}

int main(void){
    std::cin >> W >> H >> N;
    for(int i = 0; i <= N; ++i){
        int dir, dist;
        std::cin >> dir >> dist;
        locations[i] = flatLocation(dir, dist);
    }

    int sum = 0;
    for(int i = 0; i < N; ++i){
        int dist1 = std::abs(locations[i] - locations[N]);
        int dist2 = ((W + H) << 1) - dist1;
        sum += std::min(dist1, dist2);
    }
    std::cout << sum;
    
    return 0;
}