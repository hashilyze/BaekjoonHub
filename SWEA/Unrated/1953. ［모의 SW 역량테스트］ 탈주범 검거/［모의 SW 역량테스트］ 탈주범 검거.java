import java.io.*;
import java.util.*;

public class Solution {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int SIZE = 50;
	// variables
	static int N, M, R, C, L;
	static int[][] mat = new int[SIZE][SIZE];
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	
	static boolean offer(Deque<int[]> q, int ny, int nx, int join) {
		if(inRange(ny, nx) && (mat[ny][nx] & join) != 0) {
			q.offerLast(new int[] {ny, nx, mat[ny][nx]});
			mat[ny][nx] = 0;
			return true;
		}
		return false;
	}
	
	static int solution() {		
		int cnt = 0;
		
		Deque<int[]> q = new ArrayDeque<>();
		offer(q, R, C, 0x0F);
		++cnt;
		
		for(int i = 1; i < L && !q.isEmpty(); ++i) {
			for(int j = 0, size = q.size(); j < size; ++j) {
				int[] u = q.pollFirst();
				int y = u[0], x = u[1], m = u[2];
				
				if((m & 0x01) != 0 && offer(q, y - 1, x, 0x02)) ++cnt;
				if((m & 0x02) != 0 && offer(q, y + 1, x, 0x01)) ++cnt;
				if((m & 0x04) != 0 && offer(q, y, x - 1, 0x08)) ++cnt;
				if((m & 0x08) != 0 && offer(q, y, x + 1, 0x04)) ++cnt;
			}
		}
		return cnt;
	}
	
    public static void main(String[] args) throws IOException {
    	int T = readInt();
    	for(int t = 1; t <= T; ++t) {
    		N = readInt();
    		M = readInt();
    		R = readInt();
    		C = readInt();
    		L = readInt();
    		
    		for(int y = 0; y < N; ++y) {
    			for(int x = 0; x < M; ++x) {
    				switch(readInt()) {
    				// 우, 좌, 하, 상
    				case 1: mat[y][x] = 0b1111; break;
    				case 2: mat[y][x] = 0b0011; break;
    				case 3: mat[y][x] = 0b1100; break;
    				case 4: mat[y][x] = 0b1001; break;
    				case 5: mat[y][x] = 0b1010; break;
    				case 6: mat[y][x] = 0b0110; break;
    				case 7: mat[y][x] = 0b0101; break;
    				default: mat[y][x] = 0b0000; break;
    				}
    			}
    		}
    		
    		sb.append('#').append(t).append(' ').append(solution()).append('\n');
    	}
    	System.out.println(sb);
    	
    }
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}