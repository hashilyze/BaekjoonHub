import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 50;
	static final int[][] DELTA = {
			{-1, -1}, { 0, -1}, {1, -1},
			{-1,  0},           {1,  0},
			{-1,  1}, { 0,  1}, {1,  1},
	};
	// variables
	static int W, H;
	static boolean[][] isVisited = new boolean[SIZE][SIZE];

	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	static void bfs(int ay, int ax) {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(new int[] {ax, ay});
		isVisited[ay][ax] = true;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				if(inRange(ny, nx) && !isVisited[ny][nx]) {
					q.offerLast(new int[] {nx, ny});
					isVisited[ny][nx] = true;
				}
			}
		}
	}
	
	static int solution() {
		int cnt = 0;
		for(int y = 0; y < H; ++y) {
			for(int x = 0; x < W; ++x) {
				if(isVisited[y][x]) continue;
				bfs(y, x);
				++cnt;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			W = readInt();
			H = readInt();
			if(W == 0 && H == 0) break;
			
			for(int y = 0; y < H; ++y) {
				for(int x = 0; x < W; ++x) {
					isVisited[y][x] = readInt() == 0; // 탐색 불가영역(바다)를 지움
				}
			}
			sb.append(solution()).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}