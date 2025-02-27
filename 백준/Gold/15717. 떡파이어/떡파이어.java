import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static long N;
	
	static long pow(long b, long e) {
		long ans = 1;
		while(e > 0) {
			if((e & 1) == 1) ans = (ans * b) % MOD;
			b = (b * b) % MOD;
			e >>= 1;
		}
		return ans;
	}
	
	
	static int solution() {
		return (int)pow(2, N - 1);
	}
	
	public static void main(String[] args) throws IOException {
		N = Long.parseLong(br.readLine());
		
		System.out.println(solution());		
	}
}