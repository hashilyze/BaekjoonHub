import java.io.*;
import java.util.*;

/*
 * 메모리: 
 * 시간: 
 */
public class Solution {
	// IOHandler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st = null;
	
	static final int MAX_N = 20;
	static final int NBUF = -1; // 버퍼가 비어있음을 표시
	
	static int N;
	static String dir;
	static int[][] mat = new int[MAX_N][MAX_N];

	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N; 
	}
	
	static void compressLine(int[] anchor, int[] delta) {
		int buffer = NBUF;
		int wX = anchor[0], wY = anchor[1]; // 쓰기 커서
		int rX = anchor[0], rY = anchor[1]; // 읽기 커서
		
		while(inRange(rY, rX)) {
			int v = mat[rY][rX];
			if(v != 0) {
				if(buffer == NBUF) {
					buffer = v; // 버퍼에 보관: 아직 결정되지 않음
				} else {
					if(buffer == v) { // 앞축하여 작성
						mat[wY][wX] = v << 1;
						buffer = NBUF;
					} else { // 버퍼 밀어넣기
						mat[wY][wX] = buffer;
						buffer = v;
					}
					wX += delta[0];
					wY += delta[1];
				}
			}
			mat[rY][rX] = 0;
			rX += delta[0];
			rY += delta[1];
		}
		if(buffer != NBUF) {
			mat[wY][wX] = buffer;
		}
		
	}
	
	static void solution() {
		switch(dir) {
		case "up": {
			for(int x = 0; x < N; ++x) {
				compressLine(new int[]{x, 0}, new int[]{0, 1});
			}
		} break;
		case "down": {
			for(int x = 0; x < N; ++x) {
				compressLine(new int[]{x, N - 1}, new int[]{0, -1});
			}
		} break;
		case "left": {
			for(int y = 0; y < N; ++y) {
				compressLine(new int[]{0, y}, new int[]{1, 0});
			}
		} break;
		case "right": {
			for(int y = 0; y < N; ++y) {
				compressLine(new int[]{N - 1, y}, new int[]{-1, 0});
			}
		} break;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			// Input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dir = st.nextToken();
			for(int y = 0; y < N; ++y) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; ++x) {
					mat[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			solution();
			
			// Output
			sb.append("#").append(t).append("\n");
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					sb.append(mat[y][x]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}