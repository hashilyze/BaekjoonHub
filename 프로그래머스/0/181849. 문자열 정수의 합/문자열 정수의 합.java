class Solution {
    public int solution(String num_str) {
        int answer = 0;
        for(char digit : num_str.toCharArray()) answer += digit - '0';
        return answer;
    }
}