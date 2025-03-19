import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 100;
	static final int[][] DELTA = {
			{ 1, 0},
			{-1, 0},
			{ 0, 1},
			{ 0,-1},
	};
	// variables
	static int N;
	static int[][] mat = new int[MAX_N][MAX_N];
	static boolean[][] isVisited = new boolean[MAX_N][MAX_N];
	static int lower = Integer.MAX_VALUE, upper = Integer.MIN_VALUE;
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N; 
	}
	
	static void floodFill(int ay, int ax, int d) {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(new int[] {ax, ay});
		isVisited[ay][ax] = true;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				
				if(inRange(ny, nx) && !isVisited[ny][nx] && mat[ny][nx] > d) {
					q.offerLast(new int[] {nx, ny});
					isVisited[ny][nx] = true;
				}
			}
		}
	}
	
	static int solution() {
		int max = 1; // 아무 지역도 물에 잠기지 않았을 때의 영역 개수
		for(int d = lower; d <= upper; ++d) {
			// 방문 배열 초기화
			for(int y = 0; y < N; ++y) Arrays.fill(isVisited[y], 0, N, false);
			// 영역 개수 세기
			int cnt = 0;
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					if(isVisited[y][x] || mat[y][x] <= d) continue;
					floodFill(y, x, d);
					++cnt;
				}
			}
			max = Math.max(max, cnt);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				int v = mat[y][x] = readInt();
				lower = Math.min(lower, v);
				upper = Math.max(upper, v);
			}
		}
		System.out.println(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}