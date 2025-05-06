class Solution {
    public int solution(int[][] triangle) {
        for(int y = triangle.length - 2; y >= 0; --y){
            for(int x = 0; x < triangle[y].length; ++x){
                triangle[y][x] += Math.max(triangle[y+1][x], triangle[y+1][x+1]);
            }
        }
        return triangle[0][0];
    }
}