import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	static int MAX_N = 100;
	static int MAX_C = 100;
	// variables
	static int N, M;
	static int[] memories = new int[MAX_N];
	static int[] costs = new int[MAX_N];
	static int costSum;
	static int[] dp = new int[MAX_N * MAX_C + 1]; // 비용 i에서 메모리의 최댓값
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	M = readInt();
    	for(int i = 0; i < N; ++i) memories[i] = readInt();
    	for(int i = 0; i < N; ++i) costSum += costs[i] = readInt();
    	
    	for(int i = 0 ; i < N; ++i) {
    		for(int c = costSum; c >= costs[i]; --c) {
    			dp[c] = Math.max(dp[c], dp[c - costs[i]] + memories[i]);
    		}
    	}
    	
    	for(int c = 0; ; ++c) { // 메모리를 M이상 획득할 수 있는 최소 비용 탐색
    		if(dp[c] >= M) {
    			System.out.println(c);
    			break;
    		}
    	}
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	return n;
    }
}