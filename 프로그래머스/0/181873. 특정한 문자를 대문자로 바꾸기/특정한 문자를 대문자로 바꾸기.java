class Solution {
    public String solution(String my_string, String alp) {
        char target = alp.charAt(0);
        return my_string.replace(target, Character.toUpperCase(target));
    }
}