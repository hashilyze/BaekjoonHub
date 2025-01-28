class Solution {
    public int solution(int[] array, int n) {
        int cnt = 0;
        for(int e : array) if(e == n) ++cnt;
        return cnt;
    }
}