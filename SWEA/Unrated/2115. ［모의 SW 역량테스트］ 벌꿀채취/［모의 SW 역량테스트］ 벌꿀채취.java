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
	// variables
	static int N, M, C;
	static int[][] mat = new int[MAX_N][MAX_N];
	
	static int knapsack(int y, int x1, int x2) {
		int max = 0;
		int size = x2 - x1;
		for(int mask = 0x01, limit = (0x01 << size); mask < limit; ++mask) {
			int linearSum = 0;
			int squareSum = 0;
			for(int b = 0; b < size; ++b) {
				if(((mask >> b) & 1) == 1) {
					linearSum += mat[y][b + x1];
					squareSum += mat[y][b + x1] * mat[y][b + x1]; 
				}
			}
			if(linearSum <= C) max = Math.max(max, squareSum);
		}
		return max;
	}
	
	static int solution() {
		int max = 0;
		for(int y1 = 0; y1 < N; ++y1) {
			for(int x1 = 0; x1 < N - M + 1; ++x1) {
				int honey1 = knapsack(y1, x1, x1 + M);
				
				for(int x2 = x1 + M; x2 < N - M + 1; ++x2) {
					int honey2 = knapsack(y1, x2, x2 + M);
					max = Math.max(max, honey1 + honey2);
				}
				
				for(int y2 = y1 + 1; y2 < N; ++y2) {
					for(int x2 = 0; x2 < N - M + 1; ++x2) {
						int honey2 = knapsack(y2, x2, x2 + M);
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