import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static int MAX_N = 200;
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
		if(u == v) return u;
		
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
		
		for(int i = 1; i <= N; ++i) parents[i] = i; // 서로소 집합 초기화
		for(int i = 1; i <= N; ++i) { // 포레스트 생성
			for(int j = 1; j <= N; ++j) {
				if(readInt() == 1) {
					merge(i, j);
				}
			}
		}
		int u = readInt();
		for(int i = 1; i < M; ++i) {
			int v = readInt();
			if(getId(u) != getId(v)) { // u에서 v로 향하는 경로가 있는 지 확인
				System.out.println("NO");
				return;
			}
			u = v;
		}
		System.out.println("YES");
		
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
