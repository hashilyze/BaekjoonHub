class Solution {
    public int solution(int[] numbers) {
        boolean[] flags = new boolean[10];
        for(int num : numbers) flags[num] = true;
        
        int sum = 0;
        for(int i = 0; i < 10; ++i) {
            if(!flags[i]) sum += i;
        }
        return sum;
    }
}