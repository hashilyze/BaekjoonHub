import java.util.*;

class Solution {
    public String[] solution(String[] todo_list, boolean[] finished) {
        List<String> li = new ArrayList<>();
        for(int i = 0, n = todo_list.length; i < n; ++i) {
        	if(!finished[i]) li.add(todo_list[i]);
        }
        return li.toArray(new String[li.size()]);
    }
}