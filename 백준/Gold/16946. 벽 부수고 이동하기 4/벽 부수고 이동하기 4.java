import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DELTA = {
			{+1,  0},
			{-1,  0},
			{ 0, +1},
			{ 0, -1},
	};
	
	static int N, M;
	static char[][] mat;
	
	static int[][] ids;
	static int[] sizes;
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M; 
	}
	
	static int floodFill(int ay, int ax, int id) {
		int cnt = 0;
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(new int[] {ax, ay});
		ids[ay][ax] = id;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			++cnt;
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				if(inRange(ny, nx) && mat[ny][nx] == '0' && ids[ny][nx] == 0) {
					q.offerLast(new int[] {nx, ny});
					ids[ny][nx] = id;
				}
			}
		}
		return cnt;
	}
	
	static void solution() {
		int rank = 1;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(mat[y][x] == '1') continue;
				if(ids[y][x] != 0) continue;
				
				sizes[rank] = floodFill(y, x, rank);
				++rank;
			}
		}
		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(mat[y][x] == '0') continue;
				
				int sum = 1;
				Set<Integer> partitions = new HashSet<Integer>();
				for(int i = 0; i < DELTA.length; ++i) {
					int nx = x + DELTA[i][0];
					int ny = y + DELTA[i][1];
					if(inRange(ny, nx)) {
						if(partitions.add(ids[ny][nx])) {
							sum += sizes[ids[ny][nx]];
						}
					}
				}
				
				mat[y][x] = (char)(sum % 10 + '0');
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		mat = new char[N][];
		for(int y = 0; y < N; ++y) {
			mat[y] = br.readLine().toCharArray();
		}
		ids = new int[N][M];
		sizes = new int[N * M + 1];
		
		solution();
		
		// output
		for(int y = 0; y < N; ++y) {
			System.out.println(mat[y]);
		}
		
	}
}