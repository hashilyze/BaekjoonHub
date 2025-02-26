import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	static final int[][] UNIT = {{1, 0}, {0, 1}};
	static final int[][] BASE = {{1, 1}, {1, 0}};
	
	
	static int[][] product(int[][] lhs, int[][] rhs){
		int[][] ret = new int[2][2];
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				for(int k = 0; k < 2; ++k) {
					long v = ret[i][j], a = lhs[i][k], b = rhs[k][j];
					ret[i][j] = (int)((v + (a * b % MOD)) % MOD); 
				}
			}
		}
		return ret;
	}
	
	static int fib(long N) {
		int[][] ret = UNIT;
		int[][] factor = BASE;
		while(N > 0) {
			if((N & 1) == 1) ret = product(ret, factor);
			factor = product(factor, factor);
			N >>= 1;
		}
		return ret[1][0];
	}
	
	public static void main(String[] args) throws IOException {
		Long N = Long.parseLong(br.readLine());
		System.out.println(fib(N));
	}
}