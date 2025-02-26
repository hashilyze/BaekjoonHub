import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	static final int MAX_N = 4_000_000;
	
	static int[] fib = new int[MAX_N + 1];
	static int M;
	static int N, K;
	
	static void initFib() {
		fib[0] = fib[1] = 1;
		for(int i = 2; i <= MAX_N; ++i) {
			fib[i] = (int)(( (long)i * fib[i - 1]) % MOD);
		}
	}
	
	static int flt(int n, int m) {
		long r = 1;
		long b = n;
		long e = m - 2;
		while(e > 0) {
			if((e & 1) == 1) r = (int)((r * b) % m);
			b = (int)((b * b) % m);
			e >>= 1;
		}
		return (int)r;
	}
	
	static int solution() {
		K = Math.min(K, N - K);
		long a = ((long)fib[N] * flt(fib[N - K], MOD)) % MOD;
		long b = fib[K];
		return (int)(a * flt((int)b, MOD) % MOD);
	}
	
	public static void main(String[] args) throws IOException {
		initFib();
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			sb.append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}