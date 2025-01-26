import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> li = new ArrayList<>();
        for(String intStr : intStrs){
            int num = Integer.parseInt(intStr.substring(s, s + l));
            if(num > k){
                li.add(num);
            }
        }
        return li.stream().mapToInt(i->i).toArray();
    }
}