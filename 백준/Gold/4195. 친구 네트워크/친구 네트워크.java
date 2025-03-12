import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 200_000 + 10;
	// variables
	static int[] parents = new int[SIZE];
	static int[] ranks = new int[SIZE];
	static int[] counts = new int[SIZE];
	static Map<String, Integer> nameToId = new HashMap<>();
	
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static int merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return u;
		
		if(ranks[u] < ranks[v]) { // 가능한 짧은 경로를 유지하도록 병합
			int tmp = u; u = v; v = tmp;
		} else if(ranks[u] == ranks[v]) {
			++ranks[u];
		}
		parents[v] = u;
		counts[u] += counts[v];
		return u;
	}
	static void initVertex(int u) {
		parents[u] = u;
		ranks[u] = 0;
		counts[u] = 1;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			nameToId.clear();
			int F = Integer.parseInt(br.readLine());
			
			while(F-- > 0) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken(), f2 = st.nextToken();
				int id1 = nameToId.getOrDefault(f1, -1);
				int id2 = nameToId.getOrDefault(f2, -1);
				if(id1 == -1) {
					nameToId.put(f1, id1 = nameToId.size());
					initVertex(id1);
				}
				if(id2 == -1) {
					nameToId.put(f2, id2 = nameToId.size());
					initVertex(id2);
				}
				sb.append(counts[merge(id1, id2)]).append("\n");
			}
		}
		System.out.print(sb);
	}
}