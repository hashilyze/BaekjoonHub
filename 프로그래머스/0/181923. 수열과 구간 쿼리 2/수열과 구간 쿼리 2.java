class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];
        for(int j = 0; j < queries.length; ++j){
            int[] query = queries[j];
            int s = query[0], e = query[1], k = query[2];
            
            int val = -1;
            for(int i = s; i <= e; ++i){
                if(k < arr[i] && (val == -1 || val > arr[i])) {
                    val = arr[i];
                }
            }
            answer[j] = val;
        }
        return answer;
    }
}