import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int V, E;
	static int[] degrees;
	
	static int component;
	static int[] parents;
	
	
	static int find(int u) {
		if(u == parents[u]) return u;
		return parents[u] = find(parents[u]);
	}
	static void union(int u, int v) {
		u = find(u); v = find(v);
		if(u == v) return;
		
		parents[v] = u;
		--component;
	}
	
	public static void main(String[] args) throws IOException {
		V = readInt(); E = readInt();
		// 서로소 집합 초기화
		component = V;
		parents = new int[V + 1];
		for(int u = 1; u <= V; ++u) parents[u] = u;
		// 컴포넌트 생성
		degrees = new int[V+1];
		for(int i = 0; i < E; ++i) {
			int u = readInt(), v = readInt();
			++degrees[u];
			++degrees[v];
			union(u, v);
		}
		
		int odd = 0;
		for(int deg : degrees) {
			if((deg & 1) == 1) ++odd;
		}
		System.out.print(component == 1 && (odd & ~0x02) == 0 ? "YES" : "NO");
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}