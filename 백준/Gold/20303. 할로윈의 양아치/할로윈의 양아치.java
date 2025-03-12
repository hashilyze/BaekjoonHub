import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 30_000;
	// variables
	static int N, M, K;
	static int[] parents = new int[SIZE + 1]; // 서로소 집합의 ID
	static int groupCnt = 0; // 서로소 집합의 개수
	static int[] candies = new int[SIZE + 1];
	static int[] childs = new int[SIZE + 1];
	static int[][] dp = new int[2][3_001];
	
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return;
		parents[v] = u;
		candies[u] += candies[v];
		childs[u] += childs[v];
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		K = readInt();
		
		for(int i = 1; i <= N; ++i) {
			parents[i] = i; // 서로소 집합 초기화
			candies[i] = readInt();
			childs[i] = 1;
		} 
		groupCnt = N;
		
		// 집합 병합
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			merge(u, v);
		}
		
		// 서로소 집합을 모음
		List<Integer> li = new ArrayList<>();
		for(int i = 1; i <= N; ++i) {
			if(i == parents[i]) {
				li.add(i);
			}
		}
		// 냅색 문제로 K미만의 아이에게 빼앗는 최대 사탕 수 계산
		for(int i = 0; i < li.size(); ++i) {
			int i0 = i % 2;
			int i1 = (i + 1) % 2;
			
			int candy = candies[li.get(i)];
			int child = childs[li.get(i)];
			
			for(int w = 0; w <= K; ++w) {
				dp[i0][w] = dp[i1][w];
				if(w - child >= 0) dp[i0][w] = Math.max(dp[i0][w], candy + dp[i1][w - child]);				
			}
		}
		System.out.println(dp[(li.size() + 1) % 2][K - 1]);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}