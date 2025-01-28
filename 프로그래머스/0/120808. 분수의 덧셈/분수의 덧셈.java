class Solution {
    int gcd(int a, int b){
        if(a < b) {
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
    
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int n = numer1 * denom2 + numer2 * denom1;
        int d = denom1 * denom2;
        int g = gcd(n, d);
        n /= g;
        d /= g;
        
        return new int[]{n, d};
    }
}