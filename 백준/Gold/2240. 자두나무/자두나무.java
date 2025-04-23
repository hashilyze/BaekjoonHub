import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int T, W;
	static int[] locations;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		T = readInt(); W = readInt();
		locations = new int[T + 1];
		for(int i = 1; i <= T; ++i) locations[i] = readInt();
		
		int max = 0;
		dp = new int[W + 1][T + 1];
		
		for(int t = 1; t <= T; ++t) dp[0][t] = dp[0][t-1] + (locations[t] & 1);
		max = dp[0][T];
		
		for(int w = 1; w <= W; ++w) {
			for(int t = 1; t <= T; ++t) {
				dp[w][t] = Math.max(dp[w][t-1], dp[w-1][t-1]) + ((locations[t] ^ w) & 1);
			}
			max = Math.max(max, dp[w][T]);
		}
		
		System.out.print(max);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
