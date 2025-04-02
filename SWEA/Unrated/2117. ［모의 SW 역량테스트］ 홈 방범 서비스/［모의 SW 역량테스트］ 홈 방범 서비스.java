import java.io.*;
import java.util.*;

public class Solution {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int SIZE = 40;
	// variables
	static int N, M;
	static int[][] mat = new int[SIZE][SIZE];
	static int[] counts = new int[SIZE];
	
	
	static int maxValue(int ay, int ax) {		
		Arrays.fill(counts, 0);
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(mat[y][x] == 0) continue;
				int dist = Math.abs(ay - y) + Math.abs(ax - x);
				++counts[dist];
			}
		}
		
		int max = 0, cost = 1, cnt = 0;
		for(int i = 0; i < SIZE; ++i) {
			cnt += counts[i];
			if(cnt * M - cost >= 0) {
				max = Math.max(max, cnt);
			}
			cost += 4 * (i + 1);
		}
		return max;
	}
	
	static int solution() {
		int max = 0;
		for(int cy = 0; cy < N; ++cy) {
			for(int cx = 0; cx < N; ++cx) {
				max = Math.max(max, maxValue(cy, cx));
			}
		}
		return max;
	}
	
    public static void main(String[] args) throws IOException {
    	int T = readInt();
    	for(int t = 1; t <= T; ++t) {
	    	N = readInt(); M = readInt();
	    	for(int y = 0; y < N; ++y) {
	    		for(int x = 0; x < N; ++x) {
	    			mat[y][x] = readInt();
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