import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	static int MAX_K = 100_000;
	// variables
	static int N, K;
	static int[] dp = new int[MAX_K + 1];
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	K = readInt();
    	for(int i = 0; i < N; ++i) {
    		int W = readInt(), V = readInt();
    		for(int j = K; j >= W; --j) {
    			dp[j] = Math.max(dp[j], dp[j - W] + V); // 물건 i를 선택한 경우와 그렇지 않은 경우 중 최댓값
    		}
    	}
    	System.out.println(dp[K]);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}