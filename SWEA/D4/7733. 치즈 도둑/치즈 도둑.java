import java.io.*;
import java.util.*;


public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 100;
	static final int FIRST_DAY = 1;
	static final int LAST_DAY = 100;
	static final int[][] DELTA = {
			{+1,  0},
			{-1,  0},
			{ 0, +1},
			{ 0, -1},
	};
	// variables
	static int N;
	static int[][] mat = new int[MAX_N][MAX_N];
	static boolean[][] isVisited = new boolean[MAX_N][MAX_N];
	static int upper = 0; // mat에 존재하는 맛있는 정도의 최댓값
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static void floodFill(int ay, int ax, int day) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {ax, ay});
		isVisited[ay][ax] = true;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = u[0] + DELTA[i][0];
				int ny = u[1] + DELTA[i][1];
				if(inRange(ny, nx) && !isVisited[ny][nx] && mat[ny][nx] > day) {
					q.add(new int[] {nx, ny});
					isVisited[ny][nx] = true;
				}
			}
		}
	}
	
	static int countArea(int day) {
		for(int i = 0; i < N; ++i) Arrays.fill(isVisited[i], 0, N, false);
		
		int cnt = 0;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(isVisited[y][x] || mat[y][x] <= day) continue;
				floodFill(y, x, day);
				++cnt;
			}
		}
		return cnt;
	}
	
	static int solution() {
		int max = 1; // 0번째 날: 반드시 하나의 구역만 존재
		for(int i = 1; i < upper; ++i) { // 맛있는 정도가 최대가 되는 날 까지만 실행
			max = Math.max(max, countArea(i));
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			N = readInt();
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					int v = readInt();
					if((mat[y][x] = v) > upper) upper = v;
				}
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) < 0x30);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}

}
