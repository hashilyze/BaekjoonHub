import java.util.*;

class Solution {
    public int solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        
        Deque<Integer> deq = new ArrayDeque<>();
        while(st.hasMoreTokens()){
            String token = st.nextToken();
            if(token.equals("Z")) {
                deq.removeLast();
            } else {
            	deq.addLast(Integer.parseInt(token));
            }
        }
        int sum = 0;
        for(int e : deq) {
        	sum += e;
        }
        return sum;
    }
}