import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 10;
	static final int MAX_VALUE = 1001;
	// Variables	
	static int N;
	static int[] populations = new int[MAX_N];
	static boolean[][] adj = new boolean[MAX_N][MAX_N];
	
	
	static boolean isBipartied(int bitMask) {
		int cnt = 0;
		int[] isVisited = new int[N];
		Arrays.fill(isVisited, -1);
		
		// init queue
		Deque<Integer> q = new ArrayDeque<>();
		for(int i = 0; i < N; ++i) {
			if(((bitMask >> i) & 1) == 1) {
				q.offerLast(i);
				break;
			}
		}
		for(int i = 0; i < N; ++i) {
			if(((bitMask >> i) & 1) == 0) {
				q.offerLast(i);
				break;
			}
		}
		
		// flood fill
		while(!q.isEmpty()) {
			int u = q.pollFirst();
			
			if(isVisited[u] >= 0) continue;
			++cnt;
			isVisited[u] = (bitMask >> u) & 1; 
			
			for(int v = 0; v < N; ++v) {
				if(adj[u][v] && isVisited[u] == ((bitMask >> v) & 1)) {
					q.offerLast(v);
				}
			}
		}
		
		return cnt == N;
	}
	
	static int diff(int bitMask) {
		int diff = 0;
		for(int i = 0; i < N; ++i) {
			if(((bitMask >> i) & 1) == 1) {
				diff += populations[i];
			} else {
				diff -= populations[i];
			}
		}
		return Math.abs(diff);
	}
	
	static int solution() {
		int min = Integer.MAX_VALUE;
		for(int bitMask = 1, limit = (0x01 << (N - 1)); bitMask < limit;bitMask += 1) {
			if(isBipartied(bitMask)) {
				min = Math.min(min, diff(bitMask));
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int u = 0; u < N; ++u) populations[u] = nextInt();
		
		for(int u = 0; u < N; ++u) {
			st = new StringTokenizer(br.readLine());
			int K = nextInt();
			for(int i = 0; i < K; ++i) {
				int v = nextInt() - 1;
				adj[u][v] = true;
			}
		}
		
		int ans = solution();
		bw.append(""+ans).flush();
	}
	
	static int nextInt() { return Integer.parseInt(st.nextToken()); }
}
