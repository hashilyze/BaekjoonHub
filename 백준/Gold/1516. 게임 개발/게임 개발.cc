#include <iostream>
#include <cstring>

constexpr int EndSign = -1;
constexpr unsigned int MaxN = 500;

int BuildTimes[MaxN];
bool IsPrecedes[MaxN][MaxN];
int CachedTotalBuildTimes[MaxN];
int N;

int GetTotalBuildTime(int);

int main(void) { 
    // IO setup
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    std::cin >> N;
    
    std::memset(CachedTotalBuildTimes, 0xFF, sizeof(int) * MaxN);
    for (int i = 0; i < N; ++i) {
        std::cin >> BuildTimes[i];

        int precedence;
        while(std::cin >> precedence && precedence != EndSign){
            IsPrecedes[i][precedence - 1] = true;
        }
    }
    
    for(int i = 0; i < N; ++i){
        std::cout << GetTotalBuildTime(i) << "\n";
    }

    return 0; 
}

int GetTotalBuildTime(int building){
    if(CachedTotalBuildTimes[building] > 0){
        return CachedTotalBuildTimes[building];
    }

    int maxPrecedeTime = 0;
    for(int precedence = 0; precedence < N; ++precedence){
        if(IsPrecedes[building][precedence]){
            maxPrecedeTime = std::max(maxPrecedeTime, GetTotalBuildTime(precedence));
        }
    }

    return CachedTotalBuildTimes[building] = BuildTimes[building] + maxPrecedeTime;
}