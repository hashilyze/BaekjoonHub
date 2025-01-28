class Solution {
    public String solution(int age) {
        StringBuilder sb = new StringBuilder();
        while(age > 0){
            sb.append((char)(age % 10 + 'a'));
            age /= 10;
        }
        sb.reverse();
        return sb.toString();
    }
}