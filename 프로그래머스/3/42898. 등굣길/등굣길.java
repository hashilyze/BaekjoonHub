class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] mat = new int[n+1][m+1];
        for(int[] puddle : puddles){
            mat[puddle[1]][puddle[0]] = -1;
        }
        
        mat[1][1] = 1;
        for(int y = 1; y <= n; ++y){
            for(int x = 1; x <= m; ++x){
                if(mat[y][x] < 0) continue;
                
                if(mat[y-1][x] >= 0) mat[y][x] += mat[y-1][x];
                if(mat[y][x-1] >= 0) mat[y][x] += mat[y][x-1];
                mat[y][x] %= 1_000_000_007;
            }
        }
        return mat[n][m];
    }
}