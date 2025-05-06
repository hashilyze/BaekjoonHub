class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        
        int idx = 0;
        for(int i = 0; i < chars.length; ++i){
            if(chars[i] == ' ') {
                idx = 0;
                continue;
            }
            if(idx++ % 2 == 0) chars[i] = Character.toUpperCase(chars[i]);
            else chars[i] = Character.toLowerCase(chars[i]);
        }
        return new StringBuilder().append(chars).toString();
    }
}