class Solution {
    int cnt;
    int[] number;
    
    public int solution(int[] number) {
        this.cnt = 0;
        this.number = number;
        
        eachCombination(0, 3, 0);
        return cnt;
    }
    
    void eachCombination(int idx, int left, int sum){
        if(left == 0){
            if(sum == 0) ++cnt;
            return;
        }
        
        for(int i = idx; i < number.length - left + 1; ++i){
            eachCombination(i+1, left-1, sum + number[i]);
        }
    }
}