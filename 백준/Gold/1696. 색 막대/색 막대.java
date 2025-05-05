import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Bar{
		int u, v;
		Bar(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
	// constants
	static final int SIZE = 250_000; 
	// variables
	static Map<String, Integer> mapping = new HashMap<>();
	static int numBar = 0;
	static Bar[] bars = new Bar[SIZE];
	
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
		
		--component;
		parents[v] = u;
	}
	
	
	public static void main(String[] args) throws IOException {
		String line;
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			String u = st.nextToken();
			String v = st.nextToken();
			
			int keyU = mapping.computeIfAbsent(u, k->mapping.size());
			int keyV = mapping.computeIfAbsent(v, k->mapping.size());
			bars[numBar++] = new Bar(keyU, keyV);
		}
		
		degrees = new int[mapping.size()];
		component = mapping.size();
		parents = new int[mapping.size()];
		for(int u = 0; u < mapping.size(); ++u) parents[u] = u;
		
		for(int i = 0; i < numBar; ++i) {
			Bar bar = bars[i];
			++degrees[bar.u];
			++degrees[bar.v];
			union(bar.u, bar.v);
		}
		int odd = 0;
		for(int deg : degrees) {
			if(deg % 2 == 1) ++odd;
		}
		if(numBar == 0) System.out.print("Possible"); 
		else System.out.print(component == 1 && (odd & ~0x02) == 0 ? "Possible" : "Impossible");
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}