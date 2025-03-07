import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 100;
	static final int[][] DELTA = {
			{ 1,  0},
			{-1,  0},
			{ 0,  1},
			{ 0, -1},
	};
	// variables
	static int N;
	static char[][] mat = new char[MAX_N][];
	static boolean[][] isVisited = new boolean[MAX_N][MAX_N];
	
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N;  }
	
	static void floodFill(int ay, int ax) {
		char flag = mat[ay][ax];
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {ax, ay});
		isVisited[ay][ax] = true;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = u[0] + DELTA[i][0];
				int ny = u[1] + DELTA[i][1];
				if(inRange(ny, nx) && !isVisited[ny][nx] && mat[ny][nx] == flag) {
					q.offer(new int[] {nx, ny});
					isVisited[ny][nx] = true;
				}
			}
		}
	}
	static int[] solution() {
		int cnt1 = 0, cnt2 = 0;
		// 적록색약 X
		for(int i = 0; i < N; ++i) Arrays.fill(isVisited[i], 0, N, false);		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(isVisited[y][x]) continue;
				floodFill(y, x);
				++cnt1;
			}
		}
		// 적록색약 O
		for(int i = 0; i < N; ++i) Arrays.fill(isVisited[i], 0, N, false);
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(mat[y][x] == 'G') mat[y][x] = 'R';
			}
		}
		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(isVisited[y][x]) continue;
				floodFill(y, x);
				++cnt2;
			}
		}
		
		return new int[] {cnt1, cnt2};
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) mat[i] = br.readLine().toCharArray();
		int[] ans = solution();
		sb.append(ans[0]).append(" ").append(ans[1]);
		System.out.print(sb);
	}
}
