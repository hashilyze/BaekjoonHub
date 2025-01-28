import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> li = new ArrayList<>();
        for(int e : arr){
            if(li.isEmpty() || li.get(li.size() - 1) != e){
                li.add(e);
            } else {
                li.remove(li.size() - 1);
            } 
        }
        if(li.isEmpty()) return new int[]{-1};
        return li.stream().mapToInt(x->x).toArray();
    }
}