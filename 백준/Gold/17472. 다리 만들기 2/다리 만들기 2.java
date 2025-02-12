import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	// types
	static class Edge{
		public int w;
		public int u;
		public int v;
		
		public Edge() {}
		public Edge(int w, int u, int v) { this.w = w; this.u = u; this.v = v; }
	}
	
	// constants
	static final int MAX_SIZE = 10; // 최대 높이/폭
	static final int MAX_K = 10; 	// 섬의 최대 개수
	static final int DELTA = 4;
	static final int[] dy = {0, 0, 1, -1};
	static final int[] dx = {1, -1, 0, 0};
	// variables
	static int N, M; 
	static final int[][] mat = new int[MAX_SIZE][MAX_SIZE];
	static int numIsland = 0;
	static int[][] adj = new int[MAX_K][MAX_K];
	static int[] parents = new int[MAX_SIZE];
	
	
	static boolean isSafePos(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static void floodFill(int ay, int ax, int id) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] {ax, ay});
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			
			if(mat[y][x] != -1) continue;
			mat[y][x] = id;
			
			for(int i = 0; i < DELTA; ++i) {
				int nx = x + dx[i], ny = y + dy[i];
				if(isSafePos(ny, nx)) {
					q.offerLast(new int[] {nx, ny});
				}
			}
		}
	}
	
	static void makeGraph() {
		for(int i = 0; i < numIsland; ++i) {
			Arrays.fill(adj[i], Integer.MAX_VALUE);
		}
		
		// 종탐색
		{
			for(int y = 0; y < N; ++y) {
				int pX = -1;
				for(int x = 0; x < M; ++x) {
					if(mat[y][x] <= 0) continue;
					
					if(pX != -1 && mat[y][pX] != mat[y][x]) {
						int w = (x - pX) - 1;
						if(w >= 2) {
							int u = mat[y][pX] - 1;
							int v = mat[y][x] - 1;
							adj[u][v] = Math.min(adj[u][v], w);
							adj[v][u] = Math.min(adj[v][u], w);
						}
					}
					pX = x;
				}
			}
		}
		// 횡탐색
		{
			for(int x = 0; x < M; ++x) {
				int pY = -1;
				for(int y = 0; y < N; ++y) {
					if(mat[y][x] <= 0) continue;
					
					if(pY != -1 && mat[pY][x] != mat[y][x]) {
						int w = (y - pY) - 1;
						if(w >= 2) {
							int u = mat[pY][x] - 1;
							int v = mat[y][x] - 1;
							adj[u][v] = Math.min(adj[u][v], w);
							adj[v][u] = Math.min(adj[v][u], w);
						}
					}
					pY = y;
				}
			}
		}
	}
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		int uId = getId(u);
		int vId = getId(v);
		
		parents[uId] = vId;
	}
	
	static int mst() {
		for(int i = 0; i < numIsland; ++i) parents[i] = i;
		
		Queue<Edge> heap = new PriorityQueue<>((lhs, rhs)->lhs.w - rhs.w);
		for(int u = 0; u < numIsland; ++u) {
			for(int v = 0; v < numIsland; ++v) {
				if(adj[u][v] != Integer.MAX_VALUE) {
					heap.add(new Edge(adj[u][v], u, v));
				}
			}
		}
		
		int sum = 0;
		int numSet = numIsland;
		while(!heap.isEmpty()) {
			Edge edge = heap.poll();
			int uId = getId(edge.u);
			int vId = getId(edge.v);
			if(uId != vId) {
				--numSet;
				sum += edge.w;
				merge(uId, vId);
			}
		}
		return numSet == 1 ? sum : -1;
	}
	
	
	static int solution() {
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(mat[y][x] == -1) {
					floodFill(y, x, ++numIsland);
				}
			}
		}
		makeGraph();
		return mst();
	}
	

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < M; ++x) {
				mat[y][x] = -(st.nextToken().charAt(0) - '0');
			}
		}
		bw.append("" + solution()).flush();
	}
}
