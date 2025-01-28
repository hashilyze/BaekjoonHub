import java.util.*;

class Solution {
    public int solution(int n) {
        final int N = 100;
        boolean[] erathos = new boolean[N + 1];
        Arrays.fill(erathos, true);
        
        erathos[0] = erathos[1] = false;
        for(int i = 2; i <= N; ++i){
            if(!erathos[i]) continue;
            
            for(int j = i * i; j <= N; j += i){
                erathos[j] = false;
            }
        }
        
        int cnt = 0;
        for(int i = 2; i <= n; ++i){
            if(!erathos[i]) ++cnt;
        }
        return cnt;
    }
}