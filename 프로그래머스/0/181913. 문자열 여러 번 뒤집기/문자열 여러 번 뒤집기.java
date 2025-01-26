class Solution {
    public String solution(String my_string, int[][] queries) {
    	char[] arr = my_string.toCharArray();
        for(int[] query : queries){
            int s = query[0], e = query[1];
            for(int i = 0; s + i < e - i; ++i) {
            	char tmp = arr[s + i];
            	arr[s + i] = arr[e - i];
            	arr[e - i] = tmp;
            }
        }
        return new String(arr);
    }
}