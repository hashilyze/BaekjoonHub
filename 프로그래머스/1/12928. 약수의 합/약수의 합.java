class Solution {
    public int solution(int n) {
        int sum = 0;
        for(int i = 1; i * i <= n; ++i){
            if(n % i != 0) continue;
            
            sum += i;
            if(i != n / i) sum += n/i;
        }
        return sum;
    }
}