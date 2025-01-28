import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> li = new ArrayList<>();
        
        int i = 2;
        while(n > 1){
            if(n % i == 0) {
                li.add(i);
                while(n % i == 0)
                    n /= i;
            }
            ++i;
        }
        return li.stream().mapToInt(x->x).toArray();
    }
}