import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1_000;
	// variables
	static int N, M;
	static int[] parents = new int[SIZE + 1];

	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static void merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return;
		parents[v] = u;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		for(int u = 1; u <= N; ++u) parents[u] = u;
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			merge(u, v);
		}
		
		int cnt = 0;
		for(int u = 1; u <= N; ++u) {
			if(u == parents[u]) ++cnt;
		}
		System.out.print(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}