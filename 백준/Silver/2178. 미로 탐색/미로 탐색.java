import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 100;
	static final char EMPTY = '1';
	static final char BLOCK = '0';
	static final int[] START = {0, 0};
	static final int[][] DELTA = {
			{ 1,  0}, 
			{-1,  0}, 
			{ 0,  1}, 
			{ 0, -1}
	};
	// variables
	static int N, M;
	static int[][] isVisited = new int[SIZE][SIZE];
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M; 
	}
	
	static int solution() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offerLast(START);
		isVisited[START[1]][START[0]] = 1;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			if(y == N - 1 && x == M - 1) return isVisited[y][x];
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				
				if(inRange(ny, nx) && isVisited[ny][nx] == 0) {
					isVisited[ny][nx] = isVisited[y][x] + 1;
					q.offerLast(new int[] {nx, ny});
				}
			}
		}
		return -1; // 예외
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				isVisited[i][j] = readChar() - '1'; // 빈칸: 0, 벽: -1
			}
		}
		System.out.println(solution());
	}
	
	// 문자 한글자 읽기
	static int readChar() throws IOException {
		int c;
		while((c = System.in.read()) < 0x20);
		return c;
	}
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}