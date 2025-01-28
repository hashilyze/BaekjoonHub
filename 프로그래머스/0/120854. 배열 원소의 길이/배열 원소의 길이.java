class Solution {
    public int[] solution(String[] strlist) {
        final int N = strlist.length;
        int[] ans = new int[N];
        for(int i = 0; i < N; ++i){
            ans[i] = strlist[i].length();
        }
        return ans;
    }
}