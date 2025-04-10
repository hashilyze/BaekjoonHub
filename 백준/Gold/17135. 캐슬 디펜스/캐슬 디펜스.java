import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	// constants
	static int MAX_SIZE = 15;
	static int[][] DELTA = { {-1, 0}, {0, -1}, {1, 0} };
	// variables
	static int N, M, D;
	static int[][] mat = new int[MAX_SIZE][MAX_SIZE];
	static int[][] field = new int[MAX_SIZE][MAX_SIZE];
	static int endLine = MAX_SIZE; // 가장 뒤에 있는 적의 행 번호
	static int numEnemy = 0;
	static int maxKill = 0;
	
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	// 가장 가까운 적 찾기
	static int[] findEnemy(int ay, int ax, int[][] field, int visitedFlag) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {ax, ay});
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int ux = u[0], uy = u[1];
			
			if(field[uy][ux] == 1) { // 적 발견
				return new int[] { ux, uy };
			}
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = ux + DELTA[i][0], ny = uy + DELTA[i][1];
				if(inRange(ny, nx)  // 좌표 확인
					&& (Math.abs(nx - ax) + Math.abs(ny - ay) < D) // 거리 확인 
					&& (field[ny][nx] != visitedFlag) // 탐색 여부 확인
					) {
					if(field[ny][nx] != 1) field[ny][nx] = visitedFlag;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		return null; 
	}
	
	static int simulation(int locations) {
		int visitedFlag = 2;
		int cnt = 0;
		
		// 초기화
		for(int y = 0; y< N; ++y) {
			System.arraycopy(mat[y], 0, field[y], 0, M);
//			for(int x = 0; x< M; ++x) {
//				field[y][x] = mat[y][x];
//			}
		}
		List<int[]> afterKill = new ArrayList<>();
		for(int y = N; y > endLine; --y) { // 가장 마지막 적과 같은 라인에 도달할 때까지 병사가 전진
			afterKill.clear();
			for(int x = 0; x < M; ++x) { 
				if(((locations >> x) & 1) == 1) {
					int[] pos = findEnemy(y - 1, x, field, visitedFlag);
					if(pos != null) { // 적 제거
						afterKill.add(pos);
					}
					++visitedFlag;
				}
			}
			for(int[] pos : afterKill) {
				if(field[pos[1]][pos[0]] != 0) {
					field[pos[1]][pos[0]] = 0;
					++cnt;
				}
			}
			if(cnt == numEnemy) break;
		}
		
		return cnt;
	}
	
	static void nextCombination(int idx, int left, int locations) {
		if(left == 0) {
			maxKill = Math.max(maxKill, simulation(locations));
			return;
		}
		
		for(int i = idx; i < M - left + 1; ++i) {
			if(((locations >> i) & 1) == 1) continue;
			
			nextCombination(i + 1, left - 1, locations | (0x01 << i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		D = readInt();
		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				mat[y][x] = readInt();
				
				if(mat[y][x] == 1) {
					endLine = Math.min(endLine, y);
					++numEnemy;
				}
			}
		}
		
		nextCombination(0, 3, 0x00);
		System.out.print(maxKill);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
