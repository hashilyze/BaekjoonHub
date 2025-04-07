import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	static int MAX_N = 100;
	static int MAX_M = 10_000_000;
	static int INF = 101 * 100;
	// variables
	static int N, M;
	static int[] memories = new int[MAX_N];
	static int[] costs = new int[MAX_N];
	static int[] dp = new int[MAX_M + 1]; // j이상의 메모리를 얻을 수 있는 최소 비용
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	M = readInt();
    	for(int i = 0; i < N; ++i) memories[i] = readInt();
    	for(int i = 0; i < N; ++i) costs[i] = readInt();
    	
    	Arrays.fill(dp, INF);
    	for(int i = 0 ; i < N; ++i) {
    		for(int j = M; j > memories[i]; --j) dp[j] = Math.min(dp[j], dp[j - memories[i]] + costs[i]);
    		for(int j = Math.min(memories[i], M); j >= 0; --j) dp[j] = Math.min(dp[j], costs[i]);
    	}
    	System.out.println(dp[M]);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}