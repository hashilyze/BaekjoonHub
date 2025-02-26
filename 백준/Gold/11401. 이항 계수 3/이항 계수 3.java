import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 4_000_000;
	static final int MOD = 1_000_000_007;
	static final int[] FACT = new int[MAX_N + 1];
	
	static int N, K;
	
	static void initFactorial() {
		FACT[0] = FACT[1] = 1;
		for(int i = 2; i <= MAX_N; ++i) {
			FACT[i] = (int)((long)i * FACT[i - 1] % MOD); 
		}
	}
	
	static long[] xGCD(long a, long b) {
		boolean isSwap = a < b;
		if(isSwap) { long tmp = a; a = b; b = tmp; }
		
		long q;
		long r1 = a, r2 = b, r;
		long s1 = 1, s2 = 0, s;
		long t1 = 0, t2 = 1, t;
		
		while(r2 > 0) {
			q = r1 / r2;
			r = r1 % r2;
			
			s = s1 - q * s2;
			t = t1 - q * t2;
			
			r1 = r2; r2 = r;
			s1 = s2; s2 = s;
			t1 = t2; t2 = t;
		}
		
		if(isSwap) { long tmp = s1; s1 = t1; t1 = tmp; }
		return new long[] {r1, s1, t1};
	}
	
	static int inv(int n, int m) {
		long[] ret = xGCD(n, m);
		int i = (int)ret[1];
		if(i < 0) i += m;
		return i;
	}
	
	static int solution() {
		return (int)( ( (long)FACT[N] * inv(FACT[K], MOD) % MOD) * inv(FACT[N - K], MOD) % MOD);
	}
	
	public static void main(String[] args) throws IOException{
		initFactorial();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(solution());
	}

}
