import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> li = new ArrayList<>();
        
        int prev = -1;
        for(int v : arr){
            if(prev == v) continue;
            li.add(v);
            prev = v;
        }

        int[] ans = new int[li.size()];
        for(int i = 0; i < ans.length; ++i) ans[i] = li.get(i);
        return ans;
    }
}