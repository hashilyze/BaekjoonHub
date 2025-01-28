class Solution {
    public int solution(int[] arr1, int[] arr2) {
        if(arr1.length != arr2.length){
            return (int)Math.signum(arr1.length - arr2.length);
        } else {
        	int sum1 = 0, sum2 = 0;
        	for(int e : arr1) sum1 += e;
        	for(int e : arr2) sum2 += e;
        	return (int)Math.signum(sum1 - sum2);
        }
    }
}