import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        boolean[][] adj = new boolean[n+1][n+1];
        boolean[] isVisited = new boolean[n + 1];
        for(int i = 0; i < edge.length; ++i){
            int u = edge[i][0], v = edge[i][1];
            adj[u][v] = adj[v][u] = true;
        }
        
        int max = 0;
        Deque<Integer> q = new ArrayDeque<Integer>();
        isVisited[1] = true;
        q.offerLast(1);
        
        while(!q.isEmpty()){
            int cnt = 0;
            for(int i = 0, size = q.size(); i < size; ++i){
                int u = q.pollFirst();
                ++cnt;
                for(int v = 1; v <= n; ++v){
                    if(adj[u][v] && !isVisited[v]){
                        q.offerLast(v);
                        isVisited[v] = true;
                    }
                }
            }
            max = cnt;
        }
        return max;
    }
}