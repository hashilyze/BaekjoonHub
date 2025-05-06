class Solution {
    public int solution(int num) {
        if(num == 1) return 0;
        
        int cnt = 0;
        while(++cnt <= 500){
            if(num % 2 == 0) num >>= 1;
            else if(num % 2 == 1) num = num * 3 + 1;
            
            if(num == 1) return cnt;
        }
        return -1;
    }
}