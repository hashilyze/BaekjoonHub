class Solution {
    public int solution(String myString, String pat) {
        return myString.matches("(?i).*" + pat + ".*") ? 1 : 0;
    }
}