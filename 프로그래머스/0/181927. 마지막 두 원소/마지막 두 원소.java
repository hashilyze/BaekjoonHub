class Solution {
    public int[] solution(int[] num_list) {
        int[] ans = new int[num_list.length + 1];
        for(int i = 0; i < num_list.length; ++i) ans[i] = num_list[i];
        
        int e1 = num_list[num_list.length - 2];
        int e2 = num_list[num_list.length - 1];
        if(e1 < e2){
            ans[ans.length - 1] = e2 - e1;
        } else{
            ans[ans.length - 1] = e2 * 2;
        }
        return ans;
    }
}