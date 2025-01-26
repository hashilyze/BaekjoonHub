class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int j = 0; j < queries.length; ++j){
            int[] query = queries[j];
            int s = query[0], e = query[1], k = query[2];
            
            for(int i = s; i <= e; ++i){
                if(i % k == 0) ++arr[i];
            }
        }
        return arr;
    }
}