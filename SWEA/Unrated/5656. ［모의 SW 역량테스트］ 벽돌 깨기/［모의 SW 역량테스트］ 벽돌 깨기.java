import java.io.*;
import java.util.*;

public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 4;
	static final int MAX_W = 12;
	static final int MAX_H = 15;
	static int[][] DELTA = {
			{ 1,  0}, 
			{-1,  0}, 
			{ 0,  1}, 
			{ 0, -1},
		};
	// variables
	static int N, W, H;
	static int[][][] mat = new int[MAX_N + 1][MAX_H][MAX_W];
	
	
	static int explode(int nth, int by, int bx) {
		int range = mat[nth][by][bx];
		if(range == 0) return 0; // 벽돌이 없음
		
		int cnt = 1;
		mat[nth][by][bx] = 0;
		
		// 폭발 반경만큼 벽돌 제거
		for(int d = 0; d < DELTA.length; ++d) { 
			for(int r = 1; r < range; ++r) {
				int nx = bx + DELTA[d][0] * r;
				int ny = by + DELTA[d][1] * r;
				if(0 <= nx && nx < W && 0 <= ny && ny < H) {
					cnt += explode(nth, ny, nx);
				}
			}
		}
		return cnt;
	}
	static void align(int nth) {
		for(int x = 0; x < W; ++x) {
			int readY = H - 1, writeY = H - 1;
			while(readY >= 0) {
				if(mat[nth][readY][x] > 0) {
					int v = mat[nth][readY][x]; 
					mat[nth][readY][x] = 0;
					mat[nth][writeY--][x] = v;
				}
				--readY;
			}
		}
	}
	
	static int maxRemove(int nth) {
		if(nth == N + 1) 
			return 0; // 기저: N번째 구슬까지 사용함
		int max = 0;
		for(int bx = 0; bx < W; ++bx) { // 가능한 모든 위치에서 구슬을 던짐
			// 직전 상태를 가져옴
			for(int y = 0; y < H; ++y) System.arraycopy(mat[nth - 1][y], 0, mat[nth][y], 0, W);
			int by = 0;
			while(by < H && mat[nth][by][bx] == 0) ++by;
			if(by == H) continue; // by번 열에 벽돌이 없음
			int cnt = explode(nth, by, bx); // (bx, by)에 위치한 벽돌을 깨뜨림
			align(nth); // 아래 방향으로 정렬
			max = Math.max(max, cnt + maxRemove(nth + 1)); // 최댓값 갱신
		}
		return max;
	}
	
	static int solution() {
		return maxRemove(1);
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			int blocks = 0;
			N = readInt(); W = readInt(); H = readInt();
			for(int y = 0; y < H; ++y) {
				for(int x = 0; x < W; ++x) {
					if((mat[0][y][x] = readInt()) > 0) ++blocks;
				}
			}
			sb.append("#").append(t).append(" ").append(blocks - solution()).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0xF;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}