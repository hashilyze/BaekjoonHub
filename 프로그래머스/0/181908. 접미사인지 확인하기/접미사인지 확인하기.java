class Solution {
    public int solution(String my_string, String is_suffix) {
        return my_string.matches(".*" + is_suffix) ? 1 : 0;
    }
}