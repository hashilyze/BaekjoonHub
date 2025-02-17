import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 100_001;
	// variables
	static int N;
	static String A, B;
	static int[][] dp;
	
	static int solution() {
		for(int i = 1; i <= A.length(); ++i) {
			int prev_i = (i + 1) % 2;
			int mod_i = i % 2;
			
			for(int j = 1; j <= B.length(); ++j) {
				if(A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[mod_i][j] = dp[prev_i][j - 1] + 1;
				} else {
					dp[mod_i][j] = Math.max(dp[prev_i][j], dp[mod_i][j - 1]);
				}
			}
		}
		return dp[A.length() % 2][B.length()];
	}
	
	public static void main(String[] args) throws IOException {
		A = br.readLine();
		B = br.readLine();
		dp = new int[2][B.length() + 1]; // 행은 동시에 두개만 존재하면 됨
		
		bw.append(solution()+"").flush();
		
	}
}
	