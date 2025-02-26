import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DELTA = new int[][] {
		{1, 0},
		{0, 1}, 
		{-1, 0}, 
		{0, -1}, 
	};
	
	static int M, N;
	
	
	static int solution() {
		boolean[][] mat = new boolean[M][N];
		
		int next = 0;
		int cnt = 0;
		int x = 0, y = 0, d = 0;
		while(++next < M * N) {
			mat[y][x] = true;
			
			int nx = x + DELTA[d][0];
			int ny = y + DELTA[d][1];
			if((nx < 0 || N <= nx || ny < 0 || M <= ny) || mat[ny][nx]) {
				d = (d + 1) % DELTA.length;
				nx = x + DELTA[d][0];
				ny = y + DELTA[d][1];
				++cnt;
			}
			x = nx;
			y = ny;
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		System.out.println(solution());
	}

}
