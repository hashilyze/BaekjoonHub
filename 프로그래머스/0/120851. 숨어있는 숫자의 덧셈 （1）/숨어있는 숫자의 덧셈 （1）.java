class Solution {
    public int solution(String my_string) {
        int sum = 0;
        for(char ch : my_string.toCharArray()){
            if(Character.isDigit(ch)) sum += ch - '0';
        }
        return sum;
    }
}