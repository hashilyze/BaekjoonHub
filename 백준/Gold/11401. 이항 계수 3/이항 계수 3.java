import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final long MOD = 1_000_000_007;
	static long N, K;
	
	
	static long flt(long n, long m) {
		long r = 1;
		long b = n;
		long e = m - 2;
		while(e > 0) {
			if((e & 1) == 1) r = (r * b) % m;
			b = (b * b) % m;
			e >>= 1;
		}
		return r;
	}
	
	static long solution() {
		long a = 1, b = 1;
		K = Math.min(K, N - K);
		for(long i = 0; i < K; ++i) {
			a = (a * (N - i)) % MOD;
			b = (b * (K - i)) % MOD;
		}
		return a * flt(b, MOD) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		System.out.print(solution());
	}
}