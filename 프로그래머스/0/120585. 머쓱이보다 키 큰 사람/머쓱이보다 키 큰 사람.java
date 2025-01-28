class Solution {
    public int solution(int[] array, int height) {
        int cnt = 0;
        for(int e : array) {
            if(e > height) ++cnt;
        }
        return cnt;
    }
}