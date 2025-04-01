import java.io.*;
import java.util.*;
 
public class Solution {
    // IO Handler
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    // types
    // constants
    static final int MAX_N = 10;
    static final int MAX_M = 5;
    static final int MAX_C = 30;
    // variables
    static int N, M, C;
    static int[][] mat = new int[MAX_N][MAX_N];
    static int[][] dp = new int[MAX_M + 1][MAX_C + 1];
     
    static int pow2(int v) {return v * v;}
    
    static int knapsack(int y, int x) {
    	for(int m = 1; m <= M; ++m) {
    		for(int c = 1; c <= C; ++c) {
    			dp[m][c] = dp[m - 1][c];
    			
    			int v = mat[y][x + m - 1];
    			if(c - v >= 0) dp[m][c] = Math.max(dp[m][c], dp[m - 1][c - v] + pow2(v)); 
    		}
    	}
        return dp[M][C];
    }
     
    static int solution() {
        int max = 0;
        for(int y1 = 0; y1 < N; ++y1) {
            for(int x1 = 0; x1 < N - M + 1; ++x1) {
                int honey1 = knapsack(y1, x1);
                 
                for(int x2 = x1 + M; x2 < N - M + 1; ++x2) {
                    int honey2 = knapsack(y1, x2);
                    max = Math.max(max, honey1 + honey2);
                }
                 
                for(int y2 = y1 + 1; y2 < N; ++y2) {
                    for(int x2 = 0; x2 < N - M + 1; ++x2) {
                        int honey2 = knapsack(y2, x2);
                        max = Math.max(max, honey1 + honey2);
                    }
                }
                 
            }
        }
        return max;
    }
     
    public static void main(String[] args) throws IOException {
        int T = readInt();
        for(int t = 1; t <= T; ++t) {
            N = readInt(); M = readInt(); C = readInt();
            for(int y = 0; y < N; ++y) {
                for(int x = 0; x < N; ++x) {
                    mat[y][x] = readInt();
                }
            }
            sb.append('#').append(t).append(' ').append(solution()).append('\n');
        }
        System.out.println(sb);
    }
    
    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
        if(c == '\r') System.in.read();
        return n;
    }
}