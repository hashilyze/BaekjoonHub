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
	static int[] dp = new int[MAX_W + 1];  
	
	static int solution() {
		for(int i = 0; i < N; ++i) { // item[i]
			for(int w = K; w >= W[i]; --w) {
				dp[w] = Math.max(dp[w], dp[w - W[i]] + V[i]);
			}
		}
		return dp[K];
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