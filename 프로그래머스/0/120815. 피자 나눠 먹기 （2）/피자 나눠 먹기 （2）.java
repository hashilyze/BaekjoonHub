class Solution {
    int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
    int gcd(int a, int b){
        if(a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        while(b > 0){
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
    
    public int solution(int n) {
        return lcm(n, 6) / 6;
    }
}