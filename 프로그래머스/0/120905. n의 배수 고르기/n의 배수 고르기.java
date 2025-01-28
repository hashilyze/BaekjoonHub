import java.util.*;

class Solution {
    public int[] solution(int n, int[] numlist) {
        List<Integer> li = new ArrayList<>();
        for(int e : numlist) {
            if(e % n == 0) li.add(e);
        }
        return li.stream().mapToInt(x->x).toArray();
    }
}