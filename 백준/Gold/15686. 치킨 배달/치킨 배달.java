import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX_N = 50;
	static final int 
				EMPTY = 0,
				HOUSE = 1,
				CHICKEN = 2;
	static final int[][] DELTA = {
			{ 1,  0},
			{-1,  0},
			{ 0,  1},
			{ 0, -1},
	};
	
	static int N, M;
	static int houseCnt = 0;
	static int[][] houses = new int[MAX_N << 1][2];
	static int chickenCnt = 0;
	static int[][] chickens = new int[13][2];
	
	static int min = Integer.MAX_VALUE;
	
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }
	
	static int getChickenDistance(int bitMask) {
		int sum = 0;
		int[][] isVisited = new int[N][N];
		for(int i = 0; i < houseCnt; ++i) {
			int[] house = houses[i]; 
			isVisited[house[1]][house[0]] = -1;
		}
		
		Deque<int[]> q = new ArrayDeque<int[]>();
		for(int i = 0; i < chickenCnt; ++i) {
			if((bitMask & 0x01 << i) != 0) {
				int[] chicken = chickens[i]; 
				isVisited[chicken[1]][chicken[0]] = 1;
				q.offerLast(new int[] {chicken[0], chicken[1]});
			}
		}
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			
			for(int i = 0; i < DELTA.length; ++i) {
				int nx = x + DELTA[i][0];
				int ny = y + DELTA[i][1];
				if(inRange(ny, nx))  {
					if(isVisited[ny][nx] <= 0) {
						if(isVisited[ny][nx] < 0) {
							sum += isVisited[y][x];
						}
						isVisited[ny][nx] = isVisited[y][x] + 1;
						q.offerLast(new int[] {nx, ny});
					}
				}
				
			}
			
		}
		return sum;
	}
	
	static void eachCombination(int idx, int left, int bitMask) {
		if(left == 0) {
			min = Math.min(min, getChickenDistance(bitMask));
			return;
		}
		for(int i = idx; i < chickenCnt - left + 1; ++i) {
			eachCombination(i + 1, left - 1, bitMask | (0x01 << i));
		}
	}
	
	static int solution() {
		eachCombination(0, M, 0x00);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; ++x) {
				int v = Integer.parseInt(st.nextToken());
				if(v == HOUSE) {
					houses[houseCnt][0] = x;
					houses[houseCnt][1] = y;
					++houseCnt;
				} else if(v == CHICKEN) {
					chickens[chickenCnt][0] = x;
					chickens[chickenCnt][1] = y;
					++chickenCnt;
				}
			}
		}
		System.out.print(solution());
	}
}
