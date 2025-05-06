class Solution {
    public int solution(int left, int right) {
        int sum = 0;
        for(int i = left; i <= right; ++i){
            if(getCount(i) % 2 == 0) sum += i;
            else sum -= i;
        }
        return sum;
    }
    
    int getCount(int number){
        int cnt = 0;
        for(int i = 1; i <= number; ++i) {
            if(number % i == 0) ++cnt;
        }
        return cnt;
    }
}