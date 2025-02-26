import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MOD = 1_000_000_007;
	
	static int N, K;
	
	static int pow(int b, int e) {
		if(e == 0) return 1; // 기저
		
		long half = pow(b, e >> 1);
		long ret = (half * half) % MOD;
		if((e & 1) == 1) {
			ret = (ret * b) % MOD;
		}
		return (int)ret;
	}
	static int inv(int n) { return pow(n, MOD - 2); }
	
	static int solution() {
		long a = 1, b = 1;
		for(int i = 0; i < K; ++i) {
			a = (a * (N - i)) % MOD;
			b = (b * (K - i)) % MOD;
		}
		return (int)(a * inv((int)b) % MOD);
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(solution());
	}

}
