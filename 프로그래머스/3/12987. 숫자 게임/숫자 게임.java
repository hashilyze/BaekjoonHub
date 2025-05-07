import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int N = A.length;
        
        Arrays.sort(A); 
        for(int i = 0; i < A.length/2; ++i) {
            int tmp = A[i];
            A[i] = A[A.length - 1 - i];
            A[A.length - 1 - i] = tmp;
        }
        
        Arrays.sort(B);
        for(int i = 0; i < B.length/2; ++i) {
            int tmp = B[i];
            B[i] = B[B.length - 1 - i];
            B[B.length - 1 - i] = tmp;
        }
        
        int score = 0;
        int curA = 0, curB = 0;
        for(; curA < N; ++curA){
            if(B[curB] > A[curA]) {
                ++curB;
                ++score;
            }
        }
        return score;
    }
}