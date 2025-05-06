import java.util.*;

class Solution {
    public int solution(int n) {
        int ans = 0;
        for(; n > 0; n /= 3) ans = ans * 3 + (n % 3);
        return ans;
    }
}