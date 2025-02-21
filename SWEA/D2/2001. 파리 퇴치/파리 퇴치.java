import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static final int MAX_N = 15;
	
	static int N, M;
	static int[][] mat = new int[MAX_N][MAX_N];
	
	
	static int solution() {
		int max = 0;
		for(int y = 0; y < N - M + 1; ++y) {
			int sum = 0;
			// x = 0
			for(int dy = 0; dy < M; ++dy) {
				for(int dx = 0; dx < M; ++dx) {
					sum += mat[y + dy][0 + dx];
				}
			}
			max = Math.max(max, sum);
			// x > 0
			for(int x = 1; x < N - M + 1; ++x) {
				for(int dy = 0; dy < M; ++dy) {
					sum -= mat[y + dy][x - 1];
				}
				for(int dy = 0; dy < M; ++dy) {
					sum += mat[y + dy][x + M - 1];
				}
				
				max = Math.max(max, sum); 
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int y = 0; y < N; ++y) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; ++x) {
					mat[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append('#').append(t).append(" ").append(solution()).append('\n');
		}
		System.out.print(sb.toString());
	}
}
