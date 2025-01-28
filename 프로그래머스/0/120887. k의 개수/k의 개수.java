class Solution {
    public int solution(int i, int j, int k) {
        int cnt = 0;
        while(i <= j){
            int v = i++;
            
            while(v > 0){
                if(v % 10 == k) ++cnt;
                v /= 10;
            }
        }
        return cnt;
    }
}