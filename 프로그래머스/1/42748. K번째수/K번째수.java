import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> ans = new ArrayList<>();
        for(int[] command : commands){
            int i = command[0] - 1;
            int j = command[1];
            int k = command[2] - 1;
            
            List<Integer> li = new ArrayList<>();
            for(int idx = i; idx < j; ++idx) li.add(array[idx]);
            Collections.sort(li);
            //System.out.println(li.toString());
            ans.add(li.get(k));
        }
        int[] answer = new int[ans.size()];
        for(int i = 0; i < answer.length; ++i){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}