import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        final int N = emergency.length;
        int[][] arr = new int[N][3];
        for(int i = 0; i < N; ++i){
            arr[i][0] = emergency[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (lhs, rhs) -> lhs[0] - rhs[0]);
        for(int i = 0; i < N; ++i){
            arr[i][2] = N - i;
        }
        Arrays.sort(arr, (lhs, rhs) -> lhs[1] - rhs[1]);
        
        int[] ans = new int[N];
        for(int i = 0; i < N; ++i){
            ans[i] = arr[i][2];
        }
        return ans;
    }
}