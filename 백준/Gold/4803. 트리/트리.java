import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 500;
	// variables
	static int N, M;
	static int[] parents = new int[SIZE + 1];
	static int[] ranks = new int[SIZE + 1];
	static boolean[] isCycle = new boolean[SIZE + 1];
	
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) { // 사이클 발생
			isCycle[u] = true;
			return;
		}
		
		if(ranks[u] < ranks[v]) {
			int tmp = u; u = v; v = tmp;
		} else if(ranks[u] == ranks[v]) {
			++ranks[u];
		}
		parents[v] = u;
		if(isCycle[u] || isCycle[v]) isCycle[u] = isCycle[v] = true; 
	}
	
	public static void main(String[] args) throws IOException {
		int tc = 1;
		while(true) {
			N = readInt(); M = readInt();
			if(N == 0 && M == 0) break; // 종료
			
			// 서로소 집합 초기화
			for(int i = 1; i <= N; ++i) { 
				parents[i] = i;
				ranks[i] = 0;
				isCycle[i] = false;
			}
			// 간선 연결
			for(int i = 0; i < M; ++i) { 
				merge(readInt(), readInt());
			}
			// 트리의 개수 세기
			int T = 0;
			for(int i = 1; i <= N; ++i) {
				if(parents[i] == i && !isCycle[i]) {
					++T;
				}
			}
			// Output
			sb.append(String.format("Case %d: ", tc++));
			if(T == 0) sb.append("No trees.\n");
			else if(T == 1) sb.append("There is one tree.\n");
			else sb.append(String.format("A forest of %d trees.\n", T));	
		}
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