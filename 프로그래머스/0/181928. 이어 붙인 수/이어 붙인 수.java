import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        
        for(int i = 0; i < num_list.length; ++i) {
            int num = num_list[i];
            if(num % 2 == 1) s1.append("" + num_list[i]);
            else s2.append("" + num_list[i]);
        }
        
        return Integer.parseInt(s1.toString()) + Integer.parseInt(s2.toString());
    }
}