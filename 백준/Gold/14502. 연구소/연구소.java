import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 8;
	static final int EMPTY = 0, WALL = 1, VIRUS = 2;
	static final int[][] DELTA = {
			{+1,  0},
			{-1,  0},
			{ 0, +1},
			{ 0, -1},
	};
	// variables
	static int N, M;
	static int[][] mat = new int[SIZE][SIZE];
	static boolean[][] isVisited = new boolean[SIZE][SIZE];
	static List<int[]> empties = new ArrayList<>();
	static List<int[]> virus = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static int getSafeArea() {
		for(int i = 0; i < N; ++i) Arrays.fill(isVisited[i], false);
		
		int area = empties.size() - 3; // 바이러스가 전파되기 전 안전영역의 크기
		Deque<int[]> q = new ArrayDeque<int[]>(); // 바이러스 초기화
		for(int[] v : virus) {
			q.offerLast(v);
			isVisited[v[1]][v[0]] = true;
		}
		
		while(!q.isEmpty()) { // 바이러스 전파
			int[] u = q.pollFirst();
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = u[0] + DELTA[i][0];
				int ny = u[1] + DELTA[i][1];
				if(inRange(ny, nx) && mat[ny][nx] == EMPTY && !isVisited[ny][nx]) {
					q.offerLast(new int[] {nx, ny});
					isVisited[ny][nx] = true;
					--area;
				}
			}
			
		}
		return area;
	}
	
	static void eachCombination(int idx, int left) {
		if(left == 0) {
			max = Math.max(max, getSafeArea());
			return;
		}
		
		for(int i = idx; i < empties.size() - left + 1; ++i) {
			int[] pos = empties.get(i);
			mat[pos[1]][pos[0]] = WALL;
			eachCombination(i + 1, left - 1);
			mat[pos[1]][pos[0]] = EMPTY;
		}
	}
	
	static int solution() {
		eachCombination(0, 3);
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				mat[y][x] = readInt();
				if(mat[y][x] == EMPTY) empties.add(new int[] {x, y});
				else if(mat[y][x] == VIRUS) virus.add(new int[] {x, y});
				
			}
		}
		System.out.print(solution());
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
