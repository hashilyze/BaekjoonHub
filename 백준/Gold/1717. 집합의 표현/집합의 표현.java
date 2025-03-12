import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static int MAX_N = 1_000_000;
	// variables
	static int N, M;
	static int[] parents = new int[MAX_N + 1];
	static int[] ranks = new int[MAX_N + 1];
	
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static int merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return -1;
		if(ranks[u] < ranks[v]) {
			int tmp = u; u = v; v = tmp;
		} else if(ranks[u] == ranks[v]) {
			++ranks[u];
		}
		parents[v] = u;
		return u;
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i <= N; ++i) parents[i] = i;
		while(M-- > 0) {
			int cmd = readInt(), u = readInt(), v = readInt();
			switch(cmd) {
			case 0: merge(u, v); break;
			case 1: sb.append(getId(u) == getId(v) ? "YES\n" : "NO\n"); break;
			}
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
