import java.util.*;

class Solution {
    int N;
    boolean[][] adj;
    int[] minDist;
    Map<String, Integer> mapping = new HashMap<>();
    Map<Integer, String> invMapping = new HashMap<>();

    
    void bfs(int from, int to){
        Deque<Integer> q = new ArrayDeque<Integer>();
        q.offerLast(from);
        minDist[from] = 0;
        
        while(!q.isEmpty()){
            int u = q.pollFirst();
            
            for(int v = 0; v < N; ++v){
                if(adj[u][v] && minDist[v] < 0){
                    minDist[v] = minDist[u] + 1;
                    q.offerLast(v);
                }
            }
        }
    }
    
    boolean isSimillar(String s1, String s2){
        int diff = 0;
        int len = s1.length();
        for(int i = 0; i < len; ++i){
            if(s1.charAt(i) != s2.charAt(i) && ++diff >= 2) return false;
        }
        return diff != 0;
    }
    
    void insert(String s){
         if(!mapping.containsKey(s)){
            mapping.put(s, mapping.size());
            invMapping.put(invMapping.size(), s);
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        insert(begin);
        for(String w : words) insert(w);
        
        N = mapping.size();
        int from = mapping.get(begin);
        int to = mapping.getOrDefault(target, -1);
        if(to < 0) return 0; // target 단어가 사전에 없음
        
        // 인접 행렬 초기화
        adj = new boolean[N][N];
        for(int u = 0; u < N; ++u){
            for(int v = u+1; v < N; ++v){
                if(isSimillar(invMapping.get(u), invMapping.get(v))){
                    adj[u][v] = adj[v][u] = true;    
                }
            }
        }
        // 최단 거리 배열 초기화
        minDist = new int[N];
        Arrays.fill(minDist, -1);
        // 최단 거리 찾기
        bfs(from, to);
        return Math.max(0, minDist[to]);
    }
}