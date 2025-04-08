import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	// variables
	static int C, N;
	static int[] dp = new int[1000 + 101]; // dp[i]: 고객 i명을 모집하기 위한 최소 비용
	
	
	public static void main(String[] args) throws IOException {
		C = readInt(); N = readInt();
		while(N-- > 0) {
			int cost = readInt(), customer = readInt();
			
			dp[customer] = Math.min(cost, dp[customer] == 0 ? Integer.MAX_VALUE : dp[customer]);
			for(int i = customer+1; i <= C + 100; ++i) {
				if(dp[i - customer] != 0) {
					dp[i] = Math.min(dp[i - customer] + cost, dp[i] == 0 ? Integer.MAX_VALUE : dp[i]);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = C; i <= C + 100; ++i) {
			min = Math.min(min, dp[i] == 0 ? Integer.MAX_VALUE : dp[i]);
		}
		System.out.println(min);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}