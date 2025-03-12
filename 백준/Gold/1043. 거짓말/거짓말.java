import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static int MAX_N = 50;
	static int MAX_M = 50;
	// variables
	static int N, M;
	static int[] parents = new int[MAX_N + 1];
	static int[] ranks = new int[MAX_N + 1];
	static boolean[] bipartited = new boolean[MAX_N + 1];
	static List<Integer>[] parties = new List[MAX_M];
	
	static void init(int n) {
		for(int i = 1; i <= n; ++i) parents[i] = i;
	}
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
		// Input
		N = readInt(); // 사람 수
		M = readInt(); // 파티 수
		
		int tHead = 0;
		int T = readInt(); // 진실을 아는 사람 수
		for(int i = 0; i < T; ++i) bipartited[tHead = readInt()] = true;
		
		for(int i = 0; i < M; ++i) { // 파티 마다 참여하는 사람
			int P = readInt();
			parties[i] = new ArrayList<>();
			for(int j = 0; j < P; ++j) parties[i].add(readInt());
		}
		
		// Solution
		init(N); // 서로소 집합 초기화 
		for(int i = 1; i <= N; ++i) { // 진실을 아는 사람을 모두 같은 그룹으로 통합
			if(bipartited[i]) merge(tHead, i);
		}
		for(int i = 0; i < M; ++i) { // 같은 파티에 참여한 사람을 같은 그룹으로 통합
			int head = parties[i].get(0);
			for(int e : parties[i]) merge(head, e);
		}
		int cnt = 0;
		for(int i = 0; i < M; ++i) { // 거짓말을 해도 되는 파티 계수
			if(getId(parties[i].get(0)) != getId(tHead)) ++cnt;
		}
		System.out.println(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
