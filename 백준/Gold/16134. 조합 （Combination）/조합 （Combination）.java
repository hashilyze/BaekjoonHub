import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static int N, R;
	
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
		R = Math.min(R, N - R);
		long a = 1, b = 1;
		for(int i = 0; i < R; ++i) {
			a = (int)((long)a * (N - i) % MOD);
			b = (int)((long)b * (R - i) % MOD);
		}
		return (int)(a * flt((int)b, MOD) % MOD);
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		System.out.print(solution());
	}
}