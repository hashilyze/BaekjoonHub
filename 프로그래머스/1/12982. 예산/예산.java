import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int cnt = 0;
        
        Arrays.sort(d);
        for(int v : d){
            if(v > budget) break;
            budget -= v;
            ++cnt;
        }
        return cnt;
    }
}