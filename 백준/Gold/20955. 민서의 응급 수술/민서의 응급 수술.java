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
	static boolean merge(int u, int v) {
		u = getId(u); 
		v = getId(v);
		if(u == v) return false;
		
		if(ranks[u] > ranks[v]) {
			int tmp = u; u = v; v = tmp;
		}
		
		parents[v] = u;
		if(ranks[u] == ranks[v]) {
			++ranks[u];
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(merge(u, v)) { // 순환 간선은 연결하지 많음
				++cnt;
			}
		}
		cnt = (N - 1 - cnt) + (M - cnt);
		bw.append(cnt + "").flush();
	}
}
	