import java.io.*;
import java.util.*;

public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int SIZE = 1_024;
	// variables
	static int N, M, K;
	static int[][] dp = new int[SIZE + 1][SIZE + 1];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int y = 1; y <= N; ++y) {
			for(int x = 1; x <= N; ++x) {
				dp[y][x] = readInt() + dp[y][x - 1] + dp[y - 1][x] - dp[y - 1][x - 1];
			}
		}
		for(int i = 0; i < M; ++i) {
			int y1 = readInt(), x1 = readInt();
			int y2 = readInt(), x2 = readInt();
			
			int ans = dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}