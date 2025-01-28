class Solution {
    public int[] solution(int[] num_list) {
        int s = 0, e = num_list.length - 1;
        while(s < e){
            int tmp = num_list[s];
            num_list[s] = num_list[e];
            num_list[e] = tmp;
            
            ++s;
            --e;
        }
        
        return num_list;
    }
}