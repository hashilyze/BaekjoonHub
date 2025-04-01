import java.io.*;
import java.util.*;

public class Solution {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MONTH_SIZE = 12;
	// variables
	static int day1, month1, month3, year1;
	static int[] dp = new int[MONTH_SIZE + 1];
	
	
	static int solution() {
		dp[0] = 0;
		for(int m = 1; m <= MONTH_SIZE; ++m) {
			dp[m] = dp[m - 1] + Math.min(dp[m] * day1, month1);
			if(m - 3 >= 0) dp[m] = Math.min(dp[m], dp[m - 3] + month3);
		}
		return Math.min(dp[MONTH_SIZE], year1);
	}
	
    public static void main(String[] args) throws IOException {
    	int T = readInt();
    	for(int t = 1; t <= T; ++t) {
    		day1 = readInt();
    		month1 = readInt();
    		month3 = readInt();
    		year1 = readInt();
    		for(int i = 1; i <= MONTH_SIZE; ++i) dp[i] = readInt();
    		
    		sb.append('#').append(t).append(' ').append(solution()).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static int readInt() throws IOException{
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}