import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;

	static int N;
	static boolean[][] isBlockeds;
	static int[][][] dp; // <가로, 세로, 대각선>

	static int solution() {
		dp[0][1][0] = 1;
		for (int x = 2; x < N; ++x) {
			if(isBlockeds[0][x]) break;
			dp[0][x][0] += dp[0][x - 1][0];
		}
		

		for (int y = 1; y < N; ++y) {
			for (int x = 2; x < N; ++x) {
				if (isBlockeds[y][x]) continue;
				
				// 가로
				dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][2];
				// 세로
				dp[y][x][1] += dp[y - 1][x][1] + dp[y - 1][x][2];
				// 대각선
				if(!isBlockeds[y - 1][x] && !isBlockeds[y][x - 1])
					dp[y][x][2] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
			}
		}
		return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		isBlockeds = new boolean[N][N];
		dp = new int[N][N][3];

		for (int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; ++x) {
				isBlockeds[y][x] = st.nextToken().charAt(0) == '1';
			}
		}
		int ans = solution();
		bw.append(ans+"").flush();
	}

}
