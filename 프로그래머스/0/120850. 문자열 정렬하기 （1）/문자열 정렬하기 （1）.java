import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        List<Integer> li = new ArrayList<>();
        for(char ch : my_string.toCharArray()){
            if(Character.isDigit(ch))
                li.add(ch - '0');
        }
        Collections.sort(li);
        return li.stream().mapToInt(x->x).toArray();
    }
}