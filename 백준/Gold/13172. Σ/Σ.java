import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static int N;
	
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
	
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		int ret = 0;
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			ret = (int)((ret + ((long)s * flt(n, MOD) % MOD) ) % MOD);
		}
		System.out.print(ret);
	}
}