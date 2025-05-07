import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[]{-1};
        int[] ans = new int[n];
        for(int i = 0; i < n; ++i) ans[i] = s/n + (s%n > i ? 1 : 0);
        Arrays.sort(ans);
        return ans;
    }
}