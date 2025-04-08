import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	// variables
	static int C, N;
	static int[] dp = new int[100 * 1000 + 1]; // dp[i]: 비용 i로 늘릴 수 있는 최대 고객 수
	
	
	public static void main(String[] args) throws IOException {
		C = readInt(); N = readInt();
		while(N-- > 0) {
			int cost = readInt(), customer = readInt();
			for(int i = cost; i < dp.length; ++i) {
				dp[i] = Math.max(dp[i], dp[i - cost] + customer);
			}
		}
		for(int i = 0; i < dp.length; ++i) {
			if(dp[i] >= C) {
				System.out.print(i);
				break;
			}
		}
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}