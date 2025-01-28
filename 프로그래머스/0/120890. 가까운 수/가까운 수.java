import java.util.*;

class Solution {
    public int solution(int[] array, int n) {
        Arrays.sort(array);
        int ans = 1000;
        for(int i = 0; i < array.length; ++i){
            if(Math.abs(n - ans) > Math.abs(n - array[i])){
                ans = array[i];
            }
        }
        return ans;
    }
}