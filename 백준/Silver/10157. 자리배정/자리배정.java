import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static int[][] delta = {
			{0, 1}, {1, 0}, {0, -1}, {-1, 0}
	};
	// Variables
	static int C, R, K;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		int[] ans = solution();
		if(ans == null) bw.append("0");
		else bw.append((ans[0] + 1) + " " + (ans[1] + 1));
		bw.flush();
	}
	
	static int[] solution() {
		final int last = C * R;
		int[][] mat = new int[R][C];
		
		int next = 0;
		int r = 0, c = 0, d = 0;
		while(next++ < last) {
			mat[r][c] = next;
			if(next == K) return new int[] {c, r};
			
			int nc = c + delta[d][0];
			int nr = r + delta[d][1];
			if(!isSafePos(nr, nc) || mat[nr][nc] != 0) {
				d = (d + 1) % delta.length;
				nc = c + delta[d][0];
				nr = r + delta[d][1];
			}
			
			c = nc;
			r = nr;
		}
		return null;
	}
	
	static boolean isSafePos(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
