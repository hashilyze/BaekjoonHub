import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DELTA = new int[][] {
		{0, 1},
		{1, 0},
		{0, -1},
		{-1, 0},
	};
	
	static int N, TGT;
	static int[][] mat = new int[999][999];
	
	static void solution() {
		int next = N * N + 1;
		int x = 0, y = 0, d = 0;
		while(--next > 0) {
			mat[y][x] = next;
			
			int nx = x + DELTA[d][0];
			int ny = y + DELTA[d][1];
			if((nx < 0 || N <= nx || ny < 0 || N <= ny) || mat[ny][nx] > 0) {
				d = (d + 1) % DELTA.length;
				nx = x + DELTA[d][0];
				ny = y + DELTA[d][1];
			}
			x = nx;
			y = ny;
		}
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		TGT = Integer.parseInt(br.readLine());
		
		solution();
		int tx = 0, ty = 0;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				sb.append(mat[y][x]).append(" ");
				if(mat[y][x] == TGT) {
					tx = x + 1;
					ty = y + 1;
				}
			}
			sb.append("\n");
		}
		sb.append(ty + " " + tx);
		System.out.println(sb);
	}

}
