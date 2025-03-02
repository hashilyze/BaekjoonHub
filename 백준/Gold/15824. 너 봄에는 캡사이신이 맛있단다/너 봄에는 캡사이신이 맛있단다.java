import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 300_000;
	static final int MOD = 1_000_000_007;
	static final int[] POW = new int[MAX_N];
	
	static int N;
	static int[] A = new int[MAX_N];
	 
	
	static void initPow() {
		POW[0] = 1;
		for(int i = 1; i < MAX_N; ++i) {
			POW[i] = (int)(2L * POW[i - 1] % MOD);
		}
	}
	
	static int solution() {
		int ans = 0;
		for(int minIdx = 0; minIdx < N - 1; ++minIdx) {
			for(int maxIdx = minIdx + 1; maxIdx < N; ++maxIdx) {
				long diff = (A[maxIdx] - A[minIdx]) % MOD;
				long pow = POW[maxIdx - minIdx - 1];
				ans = (int)((ans + (diff * pow)) % MOD);
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		initPow();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A, 0, N);
		System.out.print(solution());
	}
}