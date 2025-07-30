import java.io.*;
import java.util.*;

public class Main {

	static final int MOD = 1_000_000;
	
	static int N;
	static int dp[][][];
	
	
	static int solve(int len, int late, int absent) {
		if(dp[len][late][absent] != -1) return dp[len][late][absent];
		if(late > 1 || absent == 3) return 0;
		
		if(len > N - 1) return 1;
		
		dp[len][late][absent] = 0;
		dp[len][late][absent] 
				= solve(len + 1, late, 0)
				+ solve(len + 1, late, absent + 1)
				+ solve(len + 1, late + 1, 0);
		
		return dp[len][late][absent] % MOD;
		
	}
	
	public static void main(String[] args) throws IOException{
		N = readInt();
		dp = new int[N + 1][3][4];
		
		for(int i = 0; i < dp.length; ++i) {
			for(int j = 0; j < dp[i].length; ++j) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(solve(0, 0, 0));
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) > 0x20) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}