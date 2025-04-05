import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N, M;
	static int[] coins = new int[20];
	static int[] dp = new int[10_001];
	
	static int solution() {
		Arrays.fill(dp, 0, M + 1, 0);
		for(int i = 0; i < N; ++i) {
			int coin = coins[i];
			dp[coin] += 1;
			for(int j = coin+1; j <= M; ++j) {
				dp[j] += dp[j - coin];
			}
		}
		return dp[M];
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			N = readInt();
			for(int i = 0; i < N; ++i) coins[i] = readInt();
			M = readInt();
			sb.append(solution()).append('\n');
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