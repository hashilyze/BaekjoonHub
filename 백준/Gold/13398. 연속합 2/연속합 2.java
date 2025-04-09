import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	// constants 
	static int MAX_N = 100_000;
	// variables
	static int N;
	static int[] A = new int[MAX_N];
	static int[][] dp = new int[MAX_N][2];
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		
		int max = A[0];
		dp[0][0] = dp[0][1] = A[0];
		for(int i = 1; i < N; ++i) {
			dp[i][0] = Math.max(dp[i - 1][0] + A[i], A[i]);
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + A[i]);
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		System.out.print(max);
    }
    
    static int readInt() throws IOException {
    	int c, n = System.in.read() & 0x0F, s = 1;
    	if(n == ('-' & 0x0F)) {
    		s = -1;
    		n = 0;
    	}
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n * s;
    }
}