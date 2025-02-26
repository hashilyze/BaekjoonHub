import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000;
	static final int[][] UNIT = new int[][] {{1, 1}, {1, 0}};
	
	
	static int[][] product(int[][] lhs, int[][] rhs){
		int[][] ret = new int[2][2];
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				for(int k = 0; k < 2; ++k) {
					ret[i][j] = (int)((ret[i][j] + ((long)lhs[i][k] * rhs[k][j]) % MOD) % MOD);
				}
			}
		}
		return ret;
	}
	
	static int[][] pow(long n){
		if(n == 1) 
			return UNIT;
		
		int[][] ret;
		
		int[][] half = pow(n >> 1);
		ret = product(half, half);
		if(n % 2 == 1) ret = product(ret, UNIT);
		return ret;
	}
	
	static int fib(long n) {
		int[][] mat = pow(n);
		return mat[1][0];
	}
	
	public static void main(String[] args) throws IOException {
		long N = Long.parseLong(br.readLine());
		System.out.println(fib(N));
	}
}