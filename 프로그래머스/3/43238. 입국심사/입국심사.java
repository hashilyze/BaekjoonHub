import java.util.*;

class Solution {
    static long getEndTime(int n, int[] times){
        int max = 0;
        for(int t : times) max = Math.max(max, t);
        return (long)n * max; // 가장 오래 걸리는 심사대로 모든 사람이 가는 경우가 최댓값
    }
    static long howManyTask(int[] times, long limit){
        long cnt = 0L;
        for(int t : times) cnt += limit / t;
        return cnt; // 주어진 시간동안 각 심사대가 처리할 수 있는 사람의 수의 총합
    }
    
    public long solution(int n, int[] times) { 
        long lo = 1, hi = getEndTime(n, times);
        while(lo < hi){
            long mid = (lo + hi) >> 1;
            if(howManyTask(times, mid) < n) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}