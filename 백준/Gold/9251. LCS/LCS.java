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
	
	static int solution() {
		for(int i = 1; i <= A.length(); ++i) {
			for(int j = 1; j <= B.length(); ++j) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + (A.charAt(i - 1) == B.charAt(j - 1) ? 1 : 0));
			}
		}
		
		return dp[A.length()][B.length()];
	}
	
	public static void main(String[] args) throws IOException {
		A = br.readLine();
		B = br.readLine();
		
		int ans = solution();
		bw.append(ans+"").flush();
		
	}
}
	