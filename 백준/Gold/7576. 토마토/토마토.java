import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 1_000;
	static final int[][] DELTA = {
			{ 1,  0}, 
			{-1,  0}, 
			{ 0,  1}, 
			{ 0, -1}
	};
	// variables
	static int N, M;
	static int[][] isVisited = new int[SIZE][SIZE];
	static List<int[]> sources = new ArrayList<>();
	static int left = 0;
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M; 
	}
	
	static int solution() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		for(int[] s : sources) { // 초기에 주어진 익은 토마토에서부터 전파 시작
			q.offerLast(s);
		}
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				
				if(inRange(ny, nx) && isVisited[ny][nx] == 0) {
					// 범위 내에 있는 익지않은 토마토에게 전파
					isVisited[ny][nx] = isVisited[y][x] + 1;
					q.offerLast(new int[] {nx, ny});
					--left;
				}
			}
			if(left == 0) {
				return isVisited[y][x];
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		M = readInt();
		N = readInt();
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				int v = isVisited[y][x] = readInt();
				if(v == 1) {
					sources.add(new int[] {x, y});
				} else if(v == 0) {
					++left;
				}
			}
		}
		if(left == 0) {
			System.out.println(0);
		} else {
			System.out.println(solution());
		}
	}
	
	// 음수를 포함한 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) < 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n * s;
	}
}