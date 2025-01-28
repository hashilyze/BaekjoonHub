class Solution {
    public String solution(String my_string) {
        StringBuilder sb = new StringBuilder();
        for(char ch : my_string.toCharArray()){
            if(Character.isUpperCase(ch)){
                sb.append((char)(ch + 0x20));
            } else{
                sb.append((char)(ch - 0x20));
            }
        }
        return sb.toString();
    }
}