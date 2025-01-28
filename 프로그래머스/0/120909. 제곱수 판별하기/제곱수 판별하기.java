class Solution {
    public int solution(int n) {
        int x = 1;
        while(x * x < n) ++x;
        return x * x == n ? 1 : 2;
    }
}