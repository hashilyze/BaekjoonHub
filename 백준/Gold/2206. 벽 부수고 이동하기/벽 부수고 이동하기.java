import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 1000;
	static final int[][] DELTA = {
			{ 1,  0},
			{-1,  0}, 
			{ 0,  1}, 
			{ 0, -1}, 
	};
	
	static int N, M;
	static boolean[][] isBlocked = new boolean[MAX_N][MAX_N];
	static int[][][] isVisited = new int[2][MAX_N][MAX_N];
	static int min = Integer.MAX_VALUE;
	
	
	static boolean isInOfRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static int solution() {
		if(N == 1 && M == 1) return 1;
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0, 0});
		isVisited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1], z = u[2];
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				
				if(isInOfRange(ny, nx) && isVisited[z][ny][nx] == 0) {
					if(!isBlocked[ny][nx]) { // 빈공간
						isVisited[z][ny][nx] = isVisited[z][y][x] + 1;
						if(ny == N - 1 && nx == M - 1) return isVisited[z][ny][nx]; // 도착점은 항상 빈칸
						q.offerLast(new int[] {nx, ny, z});
					} else if(z < 1) { // 벽을 통과할 수 있는 횟수가 남음
						isVisited[z + 1][ny][nx] = isVisited[z][y][x] + 1;
						q.offerLast(new int[] {nx, ny, z + 1});
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i) {
			String row = br.readLine();
			for(int j = 0; j < M; ++j) {
				isBlocked[i][j] = row.charAt(j) == '1';
			}
		}
		System.out.print(solution());
	}
}
