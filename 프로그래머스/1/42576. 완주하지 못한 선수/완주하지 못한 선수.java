import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> s = new HashMap<String, Integer>();
        for(String name : participant) s.put(name, s.getOrDefault(name, 0) + 1);
        for(String name : completion) {
            s.put(name, s.get(name) - 1);
        }
        for(String name : s.keySet()) {
            if(s.get(name) > 0) return name;
        }
        return null;
    }
}