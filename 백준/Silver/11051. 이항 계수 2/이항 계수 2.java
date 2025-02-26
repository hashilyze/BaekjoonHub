import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 10_007;
	static int N, K;
	
	
	static int flt(int n, int m) {
		int r = 1;
		int b = n;
		int e = m - 2;
		while(e > 0) {
			if((e & 1) == 1) r = (r * b) % m;
			b = (b * b) % m;
			e >>= 1;
		}
		return r;
	}
	
	static int solution() {
		int a = 1, b = 1;
		K = Math.min(K, N - K);
		for(int i = 0; i < K; ++i) {
			a = (a * (N - i)) % MOD;
			b = (b * (K - i)) % MOD;
		}
		return a * flt(b, MOD) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.print(solution());
	}
}