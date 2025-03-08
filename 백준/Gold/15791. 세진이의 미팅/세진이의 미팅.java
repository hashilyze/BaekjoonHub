import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MOD = 1_000_000_007;
	// variables
	static int N, M; 
	
	
	static int flt(int n, int m) {
		long ans = 1L;
		long b = n;
		long e = m - 2;
		while(e > 0) {
			if((e & 1) == 1) ans = ans * b % m;
			b = b * b % m;
			e >>= 1;
		}
		return (int)ans;
	}
	
	static int solution() {
		M = Math.min(M, N - M);
		
		long numerator = 1L, dominator = 1L;
		for(int i = 0; i < M; ++i) {
			numerator = numerator * (N - i) % MOD;
			dominator = dominator * (M - i) % MOD;
		}
		return (int)(numerator * flt((int)dominator, MOD) % MOD);
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		System.out.print(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}