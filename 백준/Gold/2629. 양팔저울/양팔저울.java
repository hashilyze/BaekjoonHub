import java.io.*;
import java.util.*;

public class Main {
	// IO Handler
	static StringBuilder sb = new StringBuilder();
	// types
	// constants
	static int MAX_W = 40_000;
	// variables
	static int N, M;
	static boolean[][] dp = new boolean[30][MAX_W + 1]; 
	
	static boolean isAble(int marble) {
		for(int w = 0; w <= MAX_W; ++w) {
			if(dp[N - 1][marble] || dp[N - 1][w] && dp[N - 1][Math.abs(marble - w)]) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		dp[0][readInt()] = true;
		for(int i = 1; i < N; ++i) {
			int W = readInt();
			
			dp[i][W] = true;
			for(int w = 1; w <= MAX_W; ++w) {
				if(dp[i - 1][w]) {
					dp[i][w] = dp[i][W + w] = dp[i][Math.abs(W - w)] = true;
				}
			}
		}
		
		M = readInt();
		for(int i = 0; i < M; ++i) {
			sb.append(dp[N - 1][readInt()] ? "Y " : "N ");
		}
		System.out.println(sb);		
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}