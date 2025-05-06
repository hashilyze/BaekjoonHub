import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] last = new int[127];
        Arrays.fill(last, -1);
        
        int[] ans = new int[s.length()];
        for(int i = 0; i < s.length(); ++i){
            char ch = s.charAt(i);
            
            if(last[ch] < 0) ans[i] = -1;
            else ans[i] = i - last[ch];
            
            last[ch] = i;
        }
        return ans;
    }
}