import java.io.*;
import java.util.*;

public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int SIZE = 50;
	static int[][] DELTA = {
		{ 1,  0}, 
		{-1,  0}, 
		{ 0,  1}, 
		{ 0, -1}, 
	};
	// variables
	static int R, C;
	static int sx, sy, dx, dy;
	static List<int[]> waters = new ArrayList<int[]>();
	static int[][] isVisited = new int[SIZE][SIZE];  // 출발지에서 최단거리
	static int[][] floodTimes = new int[SIZE][SIZE]; // 물에 잠기는 시간
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < R && 0 <= x && x < C;
	}
	
	static void initFloodTime() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		for(int[] w : waters) {
			q.offerLast(new int[] {w[0], w[1]});
			floodTimes[w[1]][w[0]] = 0;
		}
		floodTimes[dy][dx] = 0; // flood 방지
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				if(inRange(ny, nx) && floodTimes[ny][nx] == Integer.MAX_VALUE) {
					q.offerLast(new int[] {nx, ny});
					floodTimes[ny][nx] = floodTimes[y][x] + 1;
				}
			}
		}
		floodTimes[dy][dx] = Integer.MAX_VALUE; // 복원
	}
	
	static int solution() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(new int[] {sx, sy});
		isVisited[sy][sx] = 0;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				if(inRange(ny, nx) && isVisited[ny][nx] < 0 && floodTimes[ny][nx] > isVisited[y][x] + 1) {
					if(ny == dy && nx == dx) return isVisited[y][x] + 1;
					
					isVisited[ny][nx] = isVisited[y][x] + 1;
					q.offerLast(new int[] {nx, ny});
				}
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		R = readInt(); C = readInt();
		// 초기화
		for(int r = 0; r < R; ++r) {
			Arrays.fill(floodTimes[r], 0, C, Integer.MAX_VALUE);
			Arrays.fill(isVisited[r], 0, C, -1);
		}
		
		for(int r = 0; r < R; ++r) {
			char[] row = br.readLine().toCharArray();
			for(int c = 0; c < C; ++c) {
				switch(row[c]) {
				case 'S': sy = r; sx = c; break;
				case 'D': dy = r; dx = c; break;
				case '*': waters.add(new int[] {c, r}); break;
				case 'X': isVisited[r][c] = floodTimes[r][c] = 0; break;
				//case '.': break;
				}
			}
		}
		
		initFloodTime();
		int ans = solution();
		if(ans < 0) System.out.println("KAKTUS");
		else System.out.println(ans);
	}
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}