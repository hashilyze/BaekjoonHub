import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int i = 0;
        while(i < arr.length){
            if(stk.isEmpty()) {
                stk.push(arr[i++]);
            } else {
                if(stk.peek() < arr[i]) {
                    stk.push(arr[i++]);
                } else{
                    stk.pop();
                }
            }
        }
        return stk.stream().mapToInt(x->x).toArray();
    }
}