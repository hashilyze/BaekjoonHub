import java.io.*;
import java.util.*;

public class Solution {
	// IO Hanlder
	static StringBuilder sb = new StringBuilder();
	// types
	static int[][] DELTA = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	// constants
	static final int MAX_N = 4;
	static final int MAX_W = 12;
	static final int MAX_H = 15;
	// variabels
	static int N, W, H;
	static int[][][] mat = new int[MAX_N + 1][MAX_H][MAX_W];
	
	
	static int explode(int lv, int ty, int tx) {
		int cnt = 0;
		int range = mat[lv][ty][tx]; // 폭파 반경
		if(range > 0) {
			cnt = 1;
			mat[lv][ty][tx] = 0;
		}
		
		for(int d = 0; d < DELTA.length; ++d) {
			for(int r = 1; r < range; ++r) {
				int nx = tx + r * DELTA[d][0];
				int ny = ty + r * DELTA[d][1];
				
				if(0 <= ny && ny < H && 0 <= nx && nx < W) {
					if(mat[lv][ny][nx] > 0) {
						if(mat[lv][ny][nx] > 1) cnt += explode(lv, ny, nx);
						else ++cnt;
						mat[lv][ny][nx] = 0;
					}
				}
			}
			
		}
		return cnt;
	}
	
	static void compress(int[][] mat) {
		for(int x = 0; x < W; ++x) {
			int setY = H - 1; // 다음 벽돌을 배치할 커서
			for(int lookY = H - 1; lookY >= 0; --lookY) { // 벽돌을 탐색할 커서
				int ex = mat[lookY][x];
				if(ex > 0) {
					mat[lookY][x] = 0;
					mat[setY--][x] = ex;
				}
			}
		}
	}
	
	static int dfs(int lv) {
		if(lv == N + 1) return 0;
		
		int max = 0;
		for(int i = 0; i < W; ++i) {
			// 직전 상태 복사
			for(int y = 0; y < H; ++y) System.arraycopy(mat[lv - 1][y], 0, mat[lv][y], 0, W);
			// 파괴할 벽돌 찾기
			int ty = 0, tx = i;
			while(ty < H && mat[lv][ty][tx] == 0) ++ty;
			if(ty == H) continue; // 벽돌이 없음
			
			int cnt = explode(lv, ty, tx); // 폭파 후 파괴된 벽돌 개수 반환
			compress(mat[lv]); // 아래로 내리기
			max = Math.max(max, cnt + dfs(lv + 1));
		}
		return max;
	}
	
	static int solution() { 
		return dfs(1);
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			int origin = 0; // 벽돌의 초기 개수
			N = readInt(); W = readInt(); H = readInt();
			for(int y = 0; y < H; ++y) {
				for(int x = 0; x < W; ++x) {
					if((mat[0][y][x] = readInt()) != 0) ++origin;
				}
			}
			sb.append("#").append(t).append(" ").append(origin - solution()).append("\n");
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
