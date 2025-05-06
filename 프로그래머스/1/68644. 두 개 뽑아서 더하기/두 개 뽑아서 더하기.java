import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {        
        int N = numbers.length;
        
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i < N; ++i){
            for(int j = i+1; j < N; ++j){
                s.add(numbers[i]+numbers[j]);
            }
        }
        
        int[] ans = new int[s.size()];
        int idx = 0;
        for(int v : s) ans[idx++] = v;
        Arrays.sort(ans);
        
        return ans;
    }
}