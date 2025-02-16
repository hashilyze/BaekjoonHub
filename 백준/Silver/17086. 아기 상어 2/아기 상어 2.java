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
	static List<int[]> src = new ArrayList<int[]>();
	
	
	static boolean isInOfRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int solution() {
		int max = 1;
		Deque<int[]> q = new ArrayDeque<int[]>();
		for(int[] s : src) q.offerLast(s);
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int y = u[1], x = u[0];
			
			for(int d = 0; d < DELTA.length; ++d) {
				int ny = y + DELTA[d][1];
				int nx = x + DELTA[d][0];
				if(isInOfRange(ny, nx) && mat[ny][nx] == 0) {
					mat[ny][nx] = mat[y][x] + 1;
					max = Math.max(max, mat[ny][nx]);
					q.offerLast(new int[] {nx, ny});
				}
			}
		}
		return max - 1;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < M; ++x) {
				int v = Integer.parseInt(st.nextToken());
				if((mat[y][x] = v) == 1) {
					src.add(new int[] {x, y});
				}
			}
		}
		bw.append(solution()+"").flush();
	}
}
	