import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// variables
	static String A, B;
	static int[][] dp = new int[1001][1001];
	
	static String solution() {
		int lengthA = A.length();
		int lengthB = B.length();
		for(int i = 1; i <= lengthA; ++i) {
			for(int j = 1; j <= lengthB; ++j) {
				if(A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int i = lengthA, j = lengthB;
		while(dp[i][j] > 0) {
			if(dp[i][j] == dp[i - 1][j]) {
				i = i - 1;
			} else if(dp[i][j] == dp[i][j - 1]) {
				j = j - 1;
			} else { // dp[i][j] == dp[i - 1][j - 1] + 1
				sb.append(A.charAt(i - 1)); // sb.append(B.charAt(j - 1));
				i = i - 1;
				j = j - 1;
			}
		}
		sb.reverse();
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		A = br.readLine();
		B = br.readLine();
		
		String lcs = solution();
		bw.append(lcs.length() + "\n" + lcs).flush();
		
	}
}
	