class Solution {
    public int solution(int n) {
        int i = 1, x = 1;
        while(i <= n){
            i *= ++x;
        }
        return x - 1;
    }
}