import java.io.*;
import java.util.*;

class Solution {
    	public int solution(int a, int b, int c, int d) {        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(a, map.getOrDefault(a, 0) + 1);
        map.put(b, map.getOrDefault(b, 0) + 1);
        map.put(c, map.getOrDefault(c, 0) + 1);
        map.put(d, map.getOrDefault(d, 0) + 1);
        
        if(map.size() == 1) { // 4
        	return 1111 * a;
        } else if(map.size() == 2) {
        	if(map.get(a) == 2) { // 2 : 2
        		List<Integer> li = new ArrayList<>(map.keySet());
        		int p = li.get(0), q = li.get(1);
        		return (p + q) * Math.abs(p - q);
        	} else { // 3 : 1
                int p = 0, q = 0;
            	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            		if(entry.getValue() == 3) p = entry.getKey();
            		else q = entry.getKey();
            	}
            	return (10 * p + q) * (10 * p + q);
        	}
        } else if(map.size() == 3) { // 2 : 1 : 1
        	List<Integer> li = new ArrayList<>(2);
        	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
        		if(entry.getValue() == 1) li.add(entry.getKey());
        	}
        	return li.get(0) * li.get(1); // q * r
        } else { // 1 : 1 : 1 : 1
        	return Collections.min(map.keySet());
        }
    }
}