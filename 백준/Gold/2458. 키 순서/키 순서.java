import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static int MAX_N = 500;
	static int INF = MAX_N + 1;
	// variables
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];
	
	
	static void floyd(int[][] adj) {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		/* 
		 * 1. 자신보다 우선순위가 높은/낮은 학생의 수를 구한다. 
		 * 2. 등수를 알기 위해선 우선순위를 판별할 수 있는 학생 수가 (N-1)명이어야 한다.
		 */
		// Input
		N = readInt();
		M = readInt();
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		for(int i = 0; i < M; ++i) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			adj[u][v] = 1;
		}
		// Solution
		floyd(adj);
		
		int cnt = 0;
		for(int i = 0; i < N; ++i) {
			int connected = 0;
			for(int j = 0; j < N; ++j) { 				
				if(adj[i][j] != INF || adj[j][i] != INF) ++connected;
			}
			if(connected == N) ++cnt;
		}
		System.out.println(cnt);
	}
	
	// 부호없는 양의 정수 읽기
	static int readInt() throws IOException { 
		int c, n;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}