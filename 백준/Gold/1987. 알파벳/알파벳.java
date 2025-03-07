import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int SIZE = 20;
	static final int[][] DELTA = {
			{ 1,  0},
			{-1,  0},
			{ 0,  1},
			{ 0, -1},
	};
	static final int SX = 0, SY = 0;
	// variables
	static int R, C;
	static char[][] mat = new char[SIZE][];
	static int max = 0;
	static int[][] isVisited = new int[SIZE][SIZE];
	
	static boolean inRange(int y, int x) { return 0 <= y && y < R && 0 <= x && x < C; }

	static void dfs(int y, int x, int bAlphabets) {
		for(int i = 0; i < DELTA.length; ++i) {
			int nx = x + DELTA[i][0];
			int ny = y + DELTA[i][1];
			if(inRange(ny, nx) 
					&& isVisited[ny][nx] == 0 
					&& ((bAlphabets >> mat[ny][nx] - 'A') & 1) == 0
					){
				
				isVisited[ny][nx] = isVisited[y][x] + 1;
				dfs(ny, nx, bAlphabets | (0x01 << mat[ny][nx] - 'A'));
				isVisited[ny][nx] = 0;
			}
		}
		max = Math.max(max, isVisited[y][x]);
	}
	
	static int solution() {
		isVisited[SY][SX] = 1;
		dfs(SY, SX, 0x01 << mat[SY][SX] - 'A');
		isVisited[SY][SX] = 0;
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		for(int i = 0; i < R; ++i) mat[i] = br.readLine().toCharArray();
		System.out.println(solution());
	}
}