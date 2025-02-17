import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// variables
	static int N, M;
	static int a, b;
	static List<Integer>[] adj;
	
	
	static int solution() {
		boolean[] isVisited = new boolean[N];
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {a, 0});
		while(!q.isEmpty()) {
			int[] node = q.pollFirst();
			int u = node[0], ul = node[1];
			
			if(isVisited[u]) continue;
			isVisited[u] = true;
			
			if(u == b) {
				return ul;
			}
			
			for(int v : adj[u]) {
				q.add(new int[] {v, ul + 1});
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		adj = new List[N];
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()) - 1;
		b = Integer.parseInt(st.nextToken()) - 1;
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		bw.append(solution()+"").flush();
	}
}
	