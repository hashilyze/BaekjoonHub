import java.util.*;

class Solution {
public String solution(String letter) {
        String[] morseTable = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        Map<String, Character> map = new HashMap<>();
        for(int i = 0; i < 26; ++i){
            map.put(morseTable[i], (char)(i + 'a'));
        }
        
        StringBuilder ret = new StringBuilder();
        StringTokenizer st = new StringTokenizer(letter);
        
        while(st.hasMoreTokens()){
            String morse = st.nextToken();
            if(map.containsKey(morse)) {
            	ret.append(map.get(morse));
            }
        }
        return ret.toString();
    }
}