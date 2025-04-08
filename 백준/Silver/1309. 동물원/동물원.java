import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	static final int MOD = 9901; 
	// variables
	static int[][] dp = new int[3][100_001];
	
	
	public static void main(String[] args) throws IOException {
		int N = readInt();
		dp[0][0] = dp[1][0] = dp[2][0] = 1;
		for(int i = 1; i < N; ++i) {
			dp[0][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % MOD;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
		}
		System.out.println((dp[0][N - 1] + dp[1][N - 1] + dp[2][N - 1]) % MOD);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}