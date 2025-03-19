import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 25;
	static final int[][] DELTA = {
			{ 1,  0},
			{-1,  0},
			{ 0,  1},
			{ 0, -1},
	};
	// variables
	static int N;
	static boolean[][] isVisited = new boolean[SIZE][SIZE];
	static List<Integer> ans = new ArrayList<>();
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static int bfs(int ay, int ax) {
		int cnt = 1;
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
					++cnt;
				}
			}
		}
		return cnt;
	}
	
	static void solution() {
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(isVisited[y][x]) continue;
				ans.add(bfs(y, x));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				isVisited[y][x] = readDigit() == 0;
			}
		}
		
		solution();
		Collections.sort(ans);
		sb.append(ans.size()).append("\n");
		for(int cnt : ans) sb.append(cnt).append("\n");
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
	static int readDigit() throws IOException {
		int c;
		while((c = System.in.read()) <= 0x20);
		return c & 0x0F;
	}
}