import java.util.*;

class Solution {
    public int solution(String[] s1, String[] s2) {
        Arrays.sort(s1);
        Arrays.sort(s2);
        
        int cnt = 0;
        
        int i1 = 0, i2 = 0;
        while(i1 < s1.length && i2 < s2.length){
            int cmp = s1[i1].compareTo(s2[i2]);
            if(cmp < 0){
                ++i1;
            } else if(cmp > 0){
                ++i2;
            } else{
                ++i1;
                ++i2;
                ++cnt;
            }
        }
        
        return cnt;
    }
}