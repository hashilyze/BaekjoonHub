import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 101;
	// variables
	static String A, B, C;
	static int[][][] dp = new int[MAX_N][MAX_N][MAX_N];
	
	static int solution() {
		int lengthA = A.length();
		int lengthB = B.length();
		int lengthC = C.length();
		for(int i = 1; i <= lengthA; ++i) {
			for(int j = 1; j <= lengthB; ++j) {
				for(int k = 1; k <= lengthC; ++k) {
					if(A.charAt(i - 1) == B.charAt(j - 1) && B.charAt(j - 1) == C.charAt(k - 1)) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
						dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k]);
						dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j][k - 1]);
					}
				}
			}
		}
		return dp[lengthA][lengthB][lengthC];
	}
	
	public static void main(String[] args) throws IOException {
		A = br.readLine();
		B = br.readLine();
		C = br.readLine();
		
		bw.append(solution()+"").flush();
		
	}
}
	