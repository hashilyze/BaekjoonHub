class Solution {
    public int solution(int n) {
        int ans = 0;
        for(int i = 2; i <= n; i += 2) ans += i;
        return ans;
    }
}