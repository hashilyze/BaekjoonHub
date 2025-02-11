import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 16;
	// Variables	
	static int N;
	static boolean[][] isBlockeds = new boolean[MAX_N][MAX_N];
	static int[][][] dp = new int[MAX_N][MAX_N][3]; // <세로, 가로, 대각선>
	
	
	static int solution() {
		dp[0][0][1] = 1;
		for(int x = 1; x < N; ++x) {
			if(isBlockeds[0][x]) break;
			dp[0][x][1] += dp[0][x - 1][1];
		}
		
		for(int y = 1; y < N; ++y) {
			for(int x = 2; x < N; ++x) {
				if(isBlockeds[y][x]) continue;
				
				// 세로 파이프
				if(!isBlockeds[y - 1][x]) {
					dp[y][x][0] += dp[y - 1][x][0] + dp[y - 1][x][2]; 
				}
				// 가로 파이프
				if(!isBlockeds[y][x - 1]) {

					dp[y][x][1] += dp[y][x - 1][1] + dp[y][x - 1][2]; 
				}
				// 대각선 파이프
				if(!isBlockeds[y - 1][x] && !isBlockeds[y][x - 1] && !isBlockeds[y - 1][x - 1]) {
					dp[y][x][2] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
				}
			}
		}
		
		int sum = 0;
		for(int i = 0; i < 3; ++i) sum += dp[N - 1][N - 1][i];
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; ++x) {
				isBlockeds[y][x] = Integer.parseInt(st.nextToken()) == 1;
			}
		}
		bw.append(""+solution()).flush();
	}
}
	