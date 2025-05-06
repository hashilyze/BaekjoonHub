class Solution {
    public int solution(int[][] sizes) {
        int w = Integer.MIN_VALUE;
        int h = Integer.MIN_VALUE;
        for(int i = 0; i < sizes.length; ++i){
            if(sizes[i][0] > sizes[i][1]){
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            
            w = Math.max(w, sizes[i][0]);
            h = Math.max(h, sizes[i][1]);
        }
        return w*h;
    }
}