class Solution {
    public int solution(String my_string, String is_prefix) {
        return my_string.matches(is_prefix + ".*") ? 1 : 0;
    }
}