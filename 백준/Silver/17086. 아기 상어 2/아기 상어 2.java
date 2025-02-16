import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// types
	// constants
	static int[][] DELTA = new int[][] {
		{-1, 1}, {0, 1}, {1, 1},
		{-1, 0}, {1, 0},
		{-1, -1}, {0, -1}, {1, -1}
	};
	// variables
	static int N, M;
	static int[][] mat;
	
	
	static boolean isInOfRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int[] findShark(int ay, int ax) {
		boolean[][] isVisited = new boolean[N][M];
		
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(new int[] {ax, ay});
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int y = u[1], x = u[0];
			
			if(isVisited[y][x]) continue;
			isVisited[y][x] = true;
			
			if(mat[y][x] == 1) return new int[] {x, y};
			
			for(int d = 0; d < DELTA.length; ++d) {
				int ny = y + DELTA[d][1];
				int nx = x + DELTA[d][0];
				if(isInOfRange(ny, nx)) {
					q.offerLast(new int[] {nx, ny});
				}
			}
		}
		return null;
	}
	
	static int solution() {
		int max = 0;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(mat[y][x] == 1) continue;
				
				int[] pos = findShark(y, x);
				int dy = Math.abs(pos[1] - y);
				int dx = Math.abs(pos[0] - x);
				max = Math.max(max, Math.abs(dy - dx) + Math.min(dy, dx));
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < M; ++x) {
				mat[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		bw.append(solution()+"").flush();
	}
}
	