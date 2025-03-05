import java.io.*;
import java.util.*;


public class Main {
	// io
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 10;
	static final int INF = 1_000_000 * MAX_N + 1;
	static final int START = 0;
	// variables
	static int N;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int min = INF;
	
	
	static void eachPermutation(int isVisited, int u, int subDist) {
		if(subDist > min) return;
		if(isVisited == (0x01 << N) - 1) { // 기저: 마지막 도시에 도달
			min = Math.min(min, subDist + adj[u][START]);
			return;
		}
		
		for(int v = 0; v < N; ++v) {
			if(((isVisited >> v) & 1) == 1) continue;
			eachPermutation(isVisited | (0x01 << v), v, subDist + adj[u][v]);
		}
	}
	
	static int solution() {
		eachPermutation(0x01 << START, START, 0);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) Arrays.fill(adj[i], INF);
		
		for(int u = 0; u < N; ++u) {
			for(int v = 0; v < N; ++v) {
				int w = readInt();
				adj[u][v] = w != 0 ? w : INF;
			}
		}
		System.out.print(solution());
	}
	
	// 부호없는 양의 정수와 0을 읽음
	static int readInt() throws IOException {
		int c, n;
		while((c = System.in.read()) <= 0x20) ;
		n = c & 0x0F;
		while((c = System.in.read()) > 0x20) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
