#include <bits/stdc++.h>

// macros
#define FASTIO std::ios::sync_with_stdio(0); std::cin.tie(0);
//types
using pii = std::pair<int, int>;
// constants
constexpr int MAX_V = 20'000;
constexpr int MAX_E = 300'000;
constexpr int INF = 0x7FFFFFFF;
// variables
int V, E, K;
std::vector<pii> adj[MAX_V];
std::priority_queue<pii, std::vector<pii>, std::greater<pii>> pq;
int minDist[MAX_V];


void solution() {
    std::fill(minDist, minDist + V, INF);
    
    pq.push(std::make_pair(0, K));
    
    while(!pq.empty()) {
        pii u = pq.top(); pq.pop();
        if(minDist[u.second] != INF) continue;
        minDist[u.second] = u.first;
        
        for(const pii& v : adj[u.second]) {
            if(u.first + v.first < minDist[v.second]){
                pq.push(std::make_pair(u.first + v.first, v.second));
            }
        }
    }
}

int main()  {
    FASTIO
    std::cin >> V >> E;
    for(int i = 0; i < V; ++i) adj[i] = std::vector<pii>();
    
    std::cin >> K; --K;
    for(int i = 0; i < E; ++i) {
        int u, v, w;
        std::cin >> u; --u;
        std::cin >> v; --v;
        std::cin >> w;;
        adj[u].push_back(std::make_pair(w, v));
    }
    
    solution();
    
    for(int i = 0; i < V; ++i) {
        if(minDist[i] == INF) std::cout << "INF\n";
        else std::cout << minDist[i] << '\n';
    }
    return 0;
}