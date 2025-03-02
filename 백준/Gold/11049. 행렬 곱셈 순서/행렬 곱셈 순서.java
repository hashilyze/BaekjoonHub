import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[][] mat = new int[500][2];
	static int[][] dp = new int[500][500];
	
	static int solution() {
		for(int len = 2; len <= N; ++len) {
			for(int from = 0; from <= N - len; ++from) {
				int to = from + len - 1;
				dp[from][to] = Integer.MAX_VALUE;
				
				for(int div = from; div < to; ++div) {
					dp[from][to] = Math.min(dp[from][to],
							dp[from][div] + dp[div + 1][to] + (mat[from][0] * mat[to][1] * mat[div][1])
						);
				}
			}
		}
		return dp[0][N - 1];
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			mat[i][0] = Integer.parseInt(st.nextToken());
			mat[i][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solution());
	}
}