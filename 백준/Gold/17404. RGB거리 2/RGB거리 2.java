import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 3;
	static final int INF = 1_000_000_007;
	// variables
	static int N;
	static int[][] colors = new int[3][1_000];
	static int[] dp = new int[SIZE];
	
	
	static void update(int r, int g, int b) {
		r += Math.min(dp[1], dp[2]);
		g += Math.min(dp[2], dp[0]);
		b += Math.min(dp[0], dp[1]);
		
		dp[0] = r;
		dp[1] = g;
		dp[2] = b;
	}
	
	static int solution() {
		int min = Integer.MAX_VALUE;
		// red로 시작
		dp = new int[]{colors[0][0], INF, INF};
		for(int i = 1; i < N; ++i) {
			update(colors[0][i], colors[1][i], colors[2][i]);
		}
		min = Math.min(min, Math.min(dp[1], dp[2]));
		// green으로 시작
		dp = new int[]{INF, colors[1][0], INF};
		for(int i = 1; i < N; ++i) {
			update(colors[0][i], colors[1][i], colors[2][i]);
		}
		min = Math.min(min, Math.min(dp[2], dp[0]));
		// blue로 시작
		dp = new int[]{INF, INF, colors[2][0]};
		for(int i = 1; i < N; ++i) {
			update(colors[0][i], colors[1][i], colors[2][i]);
		}
		min = Math.min(min, Math.min(dp[0], dp[1]));
		
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			for(int c = 0; c < SIZE; ++c) {
				colors[c][i] = readInt();
			}
		}
		System.out.print(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}