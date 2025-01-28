import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int N = arr.length;
        while(N != (N & -N)) N += (N & -N);
        return Arrays.copyOf(arr, N);
    }
}