import java.io.*;
import java.util.*;

public class Solution {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int[][] DELTA = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	// variables
	static int N;
	static int[][] mat = new int[100][100];
	static List<int[]>[] wormholes = new List[5];
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	static int flipDirection(int d) {
		return ((d + 1) % 2) + (d / 2) * 2;
	}
	
	static int nextDirection(int y, int x, int d) {
		switch(mat[y][x]) {
		case 1: {
			if(d == 1) d = 3;
			else if(d == 2) d = 0;
			else d = flipDirection(d);
		} break;
		case 2: {
			if(d == 3) d = 0;
			else if(d == 1) d = 2;
			else d = flipDirection(d);
		} break;
		case 3: {
			if(d == 0) d = 2;
			else if(d == 3) d = 1;
			else d = flipDirection(d);
		} break;
		case 4: {
			if(d == 0) d = 3;
			else if(d == 2) d = 1;
			else d = flipDirection(d);
		} break;
		case 5: d = flipDirection(d);
		}
		return d;
	}
	static int moveWormhole(int y, int x) {
		int id = mat[y][x] - 6;
		if(wormholes[id].get(0)[0] == x && wormholes[id].get(0)[1] == y) return 1; 
		return 0;
	}
	
	static int getScore(int ay, int ax, int ad) {
		int score = 0;
		boolean isStart = false;
		int y = ay, x = ax, d = ad;
		
		
		while(true) {
			if(y == ay && x == ax) {
				if(isStart) break; // 출발점 회귀 
				isStart = true; 
			}
			if(mat[y][x] == -1) break; // 블랙홀에 빠짐
			if(1 <= mat[y][x] && mat[y][x] <= 5) { // 블록 만남
				d = nextDirection(y, x, d);
				++score;
			}
			if(6 <= mat[y][x] && mat[y][x] <= 10) { // 웜홀 만남
				int w = mat[y][x] - 6;
				int wi = moveWormhole(y, x);
				x = wormholes[w].get(wi)[0];
				y = wormholes[w].get(wi)[1];
			}
			
			// 다음 위치로 이동
			int nx = x + DELTA[d][0];
			int ny = y + DELTA[d][1];
			if(!inRange(ny, nx)) { // 벽에 부딫힘
				d = flipDirection(d); // 방향 전환
				++score;
			} else { // 이동
				y = ny;
				x = nx;
			}
		}
		return score;
	}
	
	static int solution() {
		int max = 0;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(mat[y][x] != 0) continue;
				for(int d = 0; d < DELTA.length; ++d) {
					max = Math.max(max, getScore(y, x, d));
				}
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 5; ++i) wormholes[i] = new ArrayList<>();
		
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			for(int i = 0; i < 5; ++i) wormholes[i].clear();
			
			N = readInt();
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					mat[y][x] = readInt();
					if(6 <= mat[y][x] && mat[y][x] <= 10) {
						wormholes[mat[y][x] - 6].add(new int[] {x, y});
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}
