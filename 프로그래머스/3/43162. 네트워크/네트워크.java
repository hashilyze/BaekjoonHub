class Solution {
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int N, int[][] computers) {
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){
                if(computers[i][j] == 0) computers[i][j] = INF;
            }
        }
        
        for(int k = 0; k < N; ++k){
            for(int i = 0; i < N; ++i){
                for(int j = 0; j < N; ++j){
                    if(computers[i][k] == INF || computers[k][j] == INF) continue;
                    computers[i][j] = Math.min(computers[i][j], computers[i][k]+computers[k][j]);
                }
            }
        }
        
        int component = 0;
        boolean[] isVisited = new boolean[N];
        for(int u = 0; u < N; ++u){
            if(isVisited[u]) continue;
            isVisited[u] = true;
            ++component;
            
            for(int v = 0; v < N; ++v){
                if(computers[u][v] != INF && computers[v][u] != INF){
                    isVisited[v] = true;
                }
            }
        }
        return component;
    }
}