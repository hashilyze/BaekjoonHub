import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 100_000;
	// variables
	static int N;
	static List<Integer>[] adj = new List[SIZE + 1];
	static int[] parents = new int[SIZE + 1];
	
	
	static void dfs(int u) {
		for(int v : adj[u]) {
			if(parents[v] != 0) continue;
			parents[v] = u;
			dfs(v);
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		dfs(1);
		for(int i = 2; i <= N; ++i) sb.append(parents[i]).append("\n");
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}