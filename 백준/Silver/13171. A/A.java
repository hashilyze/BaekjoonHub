import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static long A, X;
	
	static int solution() {
		long ans = 1, b = A % MOD, e = X;
		while(e > 0) {
			if((e & 1) == 1) ans = ans * b % MOD;
			e >>= 1;
			b = b * b % MOD;
		}
		return (int)ans;
	}
	public static void main(String[] args) throws IOException {
		A = Long.parseLong(br.readLine()); 
		X = Long.parseLong(br.readLine());
		System.out.print(solution());
	}
}