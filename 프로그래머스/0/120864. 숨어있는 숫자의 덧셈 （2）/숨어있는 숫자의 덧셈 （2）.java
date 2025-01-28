class Solution {
    public int solution(String my_string) {
        int sum = 0, token = 0;
        for(char ch : my_string.toCharArray()){
            if(Character.isDigit(ch)) {
                token *= 10;
                token += ch - '0';
            } else{
                sum += token;
                token = 0;
            }
        }
        sum += token;
        token = 0;
        
        return sum;
    }
}