import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node{
		int v, w;
		Node(int v, int w) {this.v = v; this.w = w;}
	}
	// constants
	static final int SIZE = 10_000;
	static final int INF = 1_000_000_007;
	static final int ROOT = 1;
	// variables
	static int N;
	static List<Node>[] adj = new List[SIZE + 1];
	static int[] minDist = new int[SIZE + 1];
	static int max;
	
	static void dfs(int u) {
		for(Node next : adj[u]) {
			if(minDist[next.v] != INF) continue;
			
			minDist[next.v] = next.w + minDist[u];
			if(minDist[max] < minDist[next.v]) {
				max = next.v; // 가장 긴 경로 찾기
			}
			dfs(next.v);	
		}
	}
	
	static int solution() {
		// 루트에서 가장 먼 정점 찾기
		max = ROOT;
		Arrays.fill(minDist, 1, N + 1, INF);
		minDist[ROOT] = 0;
		dfs(ROOT);
		int src = max;
		
		// 그 정점에서 가장 먼 정점 찾기
		max = src;
		Arrays.fill(minDist, 1, N + 1, INF);
		minDist[src] = 0;
		dfs(src);
		int dest = max;
		
		return minDist[dest];
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<Node>();
		for(int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt(), w = readInt();
			adj[u].add(new Node(v, w));
			adj[v].add(new Node(u, w));
		}
		System.out.println(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}