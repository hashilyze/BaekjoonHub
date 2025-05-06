class Solution {
    public String solution(String s, int n) {
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; ++i) {
            if(Character.isUpperCase(chars[i])){
                chars[i] = (char)((chars[i] - 'A' + n) % 26 + 'A');
            } else if(Character.isLowerCase(chars[i])){
                chars[i] = (char)((chars[i] - 'a' + n) % 26 + 'a');
            }
        }
        return new StringBuilder().append(chars).toString();
    }
}