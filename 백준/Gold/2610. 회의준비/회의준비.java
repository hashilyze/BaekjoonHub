import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 100;
	static final int INF = SIZE + 10;
	// variables
	static int N, M;
	static int[] parents = new int[SIZE + 1]; // 서로소 집합의 ID
	static int groupCnt = 0; // 서로소 집합의 개수
	static int[][] adj = new int[SIZE + 1][SIZE + 1];
	
	static int[] heads = new int[SIZE + 1]; // 대표의 ID
	static int[] delays = new int[SIZE + 1]; // 의사전달시간의 최댓값
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return;
		parents[v] = u;
		--groupCnt;
	}
	static void floyd() {
		for(int k = 1; k <= N; ++k) {
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		// 서로소 집합 초기화
		for(int i = 1; i <= N; ++i) {
			parents[i] = i;
			delays[i] = Integer.MAX_VALUE;
		}
		groupCnt = N;
		
		// 플로이드 워셜 초기화
		for(int u = 1; u <= N; ++u) {
			Arrays.fill(adj[u], 1, N + 1, INF);
			adj[u][u] = 0;
		}
		
		// 간선 생성
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			adj[u][v] = adj[v][u] = 1;
			merge(u, v);
		}
		floyd();
		
		// 각 그룹에서 다른 모든 회원과의 거리 최댓값이 가장 작은 회원을 찾음
		for(int u = 1; u <= N; ++u) {
			int maxDist = 0; // 거리 최댓값
			for(int v = 1; v <= N; ++v) { 
				if(adj[u][v] != INF) {
					maxDist = Math.max(maxDist, adj[u][v]);
				}
			}
			int p = getId(u); // 그룹 내 최소 거리합 갱신
			if(heads[p] == 0 || maxDist < delays[p]) {
				heads[p] = u;
				delays[p] = maxDist;
			}
		}
		
		// Output
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; ++i) {
			if(i == parents[i]) {
				pq.offer(heads[i]);
			}
		}
		sb.append(groupCnt).append("\n");
		while(!pq.isEmpty()) sb.append(pq.poll()).append("\n");
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