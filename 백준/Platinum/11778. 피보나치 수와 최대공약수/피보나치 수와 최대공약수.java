import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	static long N, M;
	
	
	static long gcd(long a, long b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	
	static int[][] product(int[][] a, int[][] b){
		int[][] ans = new int[2][2];
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				for(int k = 0; k < 2; ++k) {
					ans[i][j] = (int)( (ans[i][j] + ( (long)a[i][k] * b[k][j] % MOD )) % MOD ); 
				}
			}
		}		
		return ans;
	}
	
	static int fib(long n) {
		int[][] ans = {{1, 0}, {0, 1}};
		int[][] base = {{1, 1}, {1, 0}};
		long exp = n;
		while(exp > 0) {
			if((exp & 1) == 1) ans = product(ans, base);
			exp >>= 1;
			base = product(base, base);
		}
		return ans[1][0];
	}
	
	static int solution() {
		return fib(gcd(N, M));
	}
	
	public static void main(String[] args) throws IOException {	
		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		System.out.print(solution());
	}
}