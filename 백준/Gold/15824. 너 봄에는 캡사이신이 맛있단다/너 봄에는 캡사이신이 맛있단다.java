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
	
	static int mAdd(int lhs, int rhs) {
		return (int)( ((long)lhs + rhs) % MOD );
	}
	static int mSubtract(int lhs, int rhs) {
		return (int)( ((long)lhs - rhs + MOD) % MOD );
	}
	static int mMultiply(int lhs, int rhs) {
		return (int)( ((long)lhs * rhs) % MOD );
	}
	
	static void initPow() {
		POW[0] = 1;
		for(int i = 1; i < MAX_N; ++i) {
			POW[i] = (int)(2L * POW[i - 1] % MOD);
		}
	}
	
	static int solution() {
		Arrays.sort(A, 0, N);
		for(int i = 0; i < N; ++i) A[i] %= MOD;;
		
		int ans = 0;
		int lcur = 0, rcur = N - 1, sum = 0;
		while(lcur < rcur) {
			sum = mAdd(sum, mSubtract(A[rcur], A[lcur]));
			int pow = lcur != rcur - 1 ? POW[lcur] + POW[rcur - 1] : POW[lcur];
			ans = mAdd(ans, mMultiply(sum, pow));
			
			++lcur;
			--rcur;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		initPow();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) A[i] = Integer.parseInt(st.nextToken());
		
		System.out.print(solution());
	}
}