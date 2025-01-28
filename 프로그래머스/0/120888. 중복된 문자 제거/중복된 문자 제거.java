class Solution {
    public String solution(String my_string) {
        boolean[] isVisited = new boolean[127];
        
        StringBuilder sb = new StringBuilder();
        for(char ch : my_string.toCharArray()){
            if(isVisited[ch]) continue;
            
            sb.append(ch);
            isVisited[ch] = true;
        }
        return sb.toString();
    }
}