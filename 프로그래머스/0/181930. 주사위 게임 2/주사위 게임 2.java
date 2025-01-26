class Solution {
    public int solution(int a, int b, int c) {
        if(a == b){
            if(a == c){
                return func3(a, b, c);
            } else{
                return func2(a, b, c);
            }
        } else if(b == c){ 
            return func2(a, b, c);
        } else if(c == a){
            return func2(a, b, c);
        } else{ // 모두 다름
            return func1(a, b, c);
        }
    }
    
    int func1(int a, int b, int c) {
        return a + b + c;
    }
    int func2(int a, int b, int c) {
        return func1(a, b, c) * (int)(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c,  2));
    }
    int func3(int a, int b, int c) {
        return func2(a, b, c) * (int)(Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c,  3));
    }
}