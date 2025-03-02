import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	static final int MAX_N = 4_000_000;
	static final int[] FACT = new int[MAX_N + 1]; // 모듈로가 적용된 팩토리얼
	
	static int M, N, K;
	
	
	static void initFactorial() {
		FACT[0] = FACT[1] = 1;
		for(int i = 2; i <= MAX_N; ++i) {
			FACT[i] = (int)((long)FACT[i - 1] * i % MOD);
		}
	}
	
	static int flt(int n, int m) {
		long ret = 1;
		long e = m - 2;
		long b = n;
		
		while(e > 0) {
			if((e & 1) == 1) ret = ret * b % m;
			e >>= 1;
			b = b * b % MOD;
		}
		return (int)ret;
	}
	
	static int solution() {
		K = Math.min(K, N - K);
		
		long numerator = (long)FACT[N] * flt(FACT[N - K], MOD) % MOD;
		long dominator = FACT[K];
		return (int)(numerator * flt((int)dominator, MOD) % MOD);
	}
	
	public static void main(String[] args) throws IOException {
		initFactorial();
		
		M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			K = Integer.parseInt(st.nextToken());
			
			sb.append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}