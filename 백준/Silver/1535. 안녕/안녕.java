import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants
	static int MAX_N = 20;
	static int MAX_L = 100;
	// variables
	static int N;
	static int[] L = new int[MAX_N];
	static int[] J = new int[MAX_N];
	static int[] dp = new int[MAX_L + 1];
	
	
	static int solution() {
		for(int i = 0; i < N; ++i) {
			for(int l = MAX_L; l >= L[i]; --l) {
				dp[l] = Math.max(dp[l], dp[l - L[i]] + J[i]);
			}
		}
		return dp[MAX_L - 1];
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) L[i] = readInt();
		for(int i = 0; i < N; ++i) J[i] = readInt();
		System.out.println(solution());
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}