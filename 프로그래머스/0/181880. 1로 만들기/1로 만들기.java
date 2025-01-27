class Solution {
    public int solution(int[] num_list) {
        int cnt = 0;
        for(int num : num_list){
            while(num > 1){
                if(num % 2 == 1){
                    num = (num - 1) / 2;
                } else{
                    num /= 2;
                }
                ++cnt;
            }
        }
        return cnt;
    }
}