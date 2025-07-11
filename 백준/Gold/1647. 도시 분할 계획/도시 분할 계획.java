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
	static int N, M;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((lhs, rhs)->lhs[2]-rhs[2]);
	static int groupCnt;
	static int[] parents;
	
	static int getId(int u) {
		if(parents[u] == 0) return u;
		return parents[u] = getId(parents[u]);
	}
	
	static boolean merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return false;
		parents[v] = u;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		for(int i = 0; i < M; ++i) pq.offer(new int[] {readInt(), readInt(), readInt()});
		
		int sum = 0;
		groupCnt = N;
		parents = new int[N + 1];
		while(groupCnt > 2) {
			int[] cur = pq.poll();
			if(merge(cur[0], cur[1])) {
				sum += cur[2];
				--groupCnt;
			}
		}
		System.out.print(sum);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}