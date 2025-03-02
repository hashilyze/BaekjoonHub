import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static int T, N;
	
	static int pow(int b, int e) {
		long ans = 1;
		while(e > 0) {
			if((e & 1) == 1) ans = ans * b % MOD;
			e >>= 1;
			b = (int)((long)b * b % MOD);
		}
		return (int)ans;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			sb.append(pow(2, N - 2)).append("\n");
		}
		System.out.println(sb);
	}
}