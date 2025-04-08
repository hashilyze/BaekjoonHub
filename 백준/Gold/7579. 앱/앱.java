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
	static int[] dp = new int[MAX_N * MAX_C + 1]; // dp[i]: 비용 i로 확보할 수 있는 최대 메모리
	
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		for(int i = 0; i < N; ++i) memories[i] = readInt();
		for(int i = 0; i < N; ++i) costs[i] = readInt();
		
		for(int i = 0; i < N; ++i) {
			for(int c = MAX_N * MAX_C; c >= costs[i]; --c) {
				dp[c] = Math.max(dp[c], dp[c - costs[i]] + memories[i]);
			}
		}
		
		int c = -1;
		while(dp[++c] < M);
		System.out.print(c);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}