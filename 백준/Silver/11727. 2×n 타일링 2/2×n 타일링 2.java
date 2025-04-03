import java.io.*;
import java.util.*;


public class Main {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MOD = 10_007;
	// variables
	static int N;
	static int[] dp = new int[3];
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	dp[1] = 1; dp[2] = 3;
    	for(int i = 3; i <= N; ++i) {
    		dp[i % 3] = (dp[(i + 2) % 3] + (dp[(i + 1) % 3] << 1)) % MOD;
    	}
    	System.out.println(dp[N % 3]);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}