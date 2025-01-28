import java.util.*;

class Solution {
    public String solution(String rsp) {
        Map<Character, Character> map = new HashMap<>();
        map.put('2', '0');
        map.put('0', '5');
        map.put('5', '2');
        
        StringBuilder sb = new StringBuilder();
        for(char ch : rsp.toCharArray()){
            sb.append(map.get(ch));
        }
        return sb.toString();
    }
}