import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	// constants 
	static int MAX_N = 100_000;
	// variables
	static int N;
	static int[]dp = new int[MAX_N];
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		
		int max = dp[0] = readInt();
		for(int i = 1; i < N; ++i) {
			int A = readInt();
			dp[i] = Math.max(dp[i - 1] + A, A);
			max = Math.max(max, dp[i]);
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