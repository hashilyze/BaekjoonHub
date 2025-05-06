import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int sum = 0, cnt = 0;
        
        Arrays.sort(d);
        for(int v : d){
            if(sum + v > budget) break;
            sum += v;
            ++cnt;
        }
        return cnt;
    }
}