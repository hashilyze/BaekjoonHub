import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 100;
	static final int MAX_W = 100_000;
	// variables
	static int N, K;
	static int[] W = new int[MAX_N];
	static int[] V = new int[MAX_N];
	// dp[i][j]: 순서대로 i개의 물건 중에서 무게의 합이 j이하가 되도록 선택하였을 때 가치의 최댓값 
	static int[][] dp = new int[MAX_N + 1][MAX_W + 1];  
	
	static int solution() {
		for(int i = 0; i < N; ++i) { // item[i]
			for(int w = 1; w <= K; ++w) {
				dp[i + 1][w] = dp[i][w]; // i번째 물건을 물건을 선택하지 않음
				if(w - W[i] >= 0) { // i번째 물건을 물건을 선택함
					dp[i + 1][w] = Math.max(dp[i + 1][w], dp[i][w - W[i]] + V[i]);
				}
			}
		}
		return dp[N][K];
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		for(int i = 0; i < N; ++i) {
			W[i] = readInt();
			V[i] = readInt();
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