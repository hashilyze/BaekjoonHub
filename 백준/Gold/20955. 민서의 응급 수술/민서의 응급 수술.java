import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 100_000;
	// variables
	static int N, M;
	static int[] parents = new int[MAX_N + 1];
	static int[] ranks = new int[MAX_N + 1];
	
	
	static int getId(int u) {
		if(parents[u] == 0) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		u = getId(u); 
		v = getId(v);
		
		if(ranks[u] > ranks[v]) {
			int tmp = u; u = v; v = tmp;
		}
		
		parents[v] = u;
		if(ranks[u] == ranks[v]) {
			++ranks[u];
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int rm = 0, add = 0;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(getId(u) == getId(v)) { // 순환 간선은 연결하지 많음
				++rm;
			} else {
				merge(u, v);
			}
		}
		add = Math.max(0, (N - 1) - (M - rm)); // merge 횟수가 (N - 1)보다 작은 만큼 연결
		
		bw.append((rm + add) + "").flush();
	}
}
	