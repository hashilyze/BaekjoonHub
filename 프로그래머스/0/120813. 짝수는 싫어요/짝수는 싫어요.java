import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> li = new ArrayList<>();
        for(int i = 1; i <= n; i += 2) li.add(i);
        return li.stream().mapToInt(x->x).toArray();
    }
}