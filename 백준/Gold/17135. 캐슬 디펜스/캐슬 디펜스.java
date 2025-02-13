import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	// constants
	static int MAX_SIZE = 15;
	static int[][] DELTA = new int[][] {
		{-1, 0}, {0, -1}, {1, 0}
	};
	// variables
	static int N, M, D;
	static int[][] mat = new int[MAX_SIZE][MAX_SIZE];
	static int endLine = MAX_SIZE; // 가장 뒤에 있는 적의 행 번호
	static int numEnemy = 0;
	static int result = 0;
	
	static boolean isSafePos(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
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
				if(isSafePos(ny, nx)  // 좌표 확인
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
	
	static int simulation(boolean[] positions) {
		int visitedFlag = 2;
		int cnt = 0;
		
		// 초기화
		int[][] field = new int[N][M];
		for(int y = 0; y< N; ++y) {
			for(int x = 0; x< M; ++x) {
				field[y][x] = mat[y][x];
			}
		}
		List<int[]> afterKill = new ArrayList<>();
		for(int y = N; y > endLine; --y) { // 가장 마지막 적과 같은 라인에 도달할 때까지 병사가 전진
			afterKill.clear();
			for(int x = 0; x < M; ++x) { 
				if(positions[x]) {
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
	
	static void nextCombination(int left, int idx, int size, boolean[] isUsed) {
		if(left == 0) {
			result = Math.max(result, simulation(isUsed));
			return;
		}
		
		for(int i = idx; i < size - left + 1; ++i) {
			if(isUsed[i]) continue;
			
			isUsed[i] = true;
			nextCombination(left - 1, i + 1, size, isUsed);
			isUsed[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < M; ++x) {
				mat[y][x] = st.nextToken().charAt(0) - '0';
				
				if(mat[y][x] == 1) {
					endLine = Math.min(endLine, y);
					++numEnemy;
				}
			}
		}
		
		boolean[] isUsed = new boolean[M];
		nextCombination(3, 0, M, isUsed);
		bw.append(""+result).flush();
	}
}
