class Solution {
    public String solution(String s) {
        int[] freq = new int[26];
        for(char ch : s.toCharArray()){
            ++freq[ch - 'a'];
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < freq.length; ++i){
            if(freq[i] == 1)
                sb.append((char)(i + 'a'));
        }
        return sb.toString();
    }
}