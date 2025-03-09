import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 500_000;
	static final int SIZE = 3;
	static final char[] COLOR_TABLE = new char[] {'R', 'G', 'B'};
	static final int SRC = 1;
	// variables
	static int N;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static int[][] colors = new int[3][MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];
	
	static int max;
	static int[] bulbs = new int[MAX_N + 1];
	
	static void makeMaxVlaue(int p, int u) {
		if(isVisited[u]) return;
		isVisited[u] = true;
		for(int v : adj[u]) makeMaxVlaue(u, v);
		
		if(p != 0) {
			colors[0][p] += Math.max(colors[1][u], colors[2][u]);
			colors[1][p] += Math.max(colors[2][u], colors[0][u]);
			colors[2][p] += Math.max(colors[0][u], colors[1][u]);
		}
	}
	
	static void makeBulbColor(int pc, int u) {
		if(isVisited[u]) return;
		isVisited[u] = true;
		
		int c1 = (pc + 1) % SIZE;
		int c2 = (pc + 2) % SIZE;
		
		int c = colors[c1][u] < colors[c2][u] ? c2 : c1; 
		bulbs[u] = c;
		
		for(int v : adj[u]) makeBulbColor(c, v);
	}
	
	static void solution() {
		makeMaxVlaue(0, SRC);
		max = Math.max(Math.max(colors[0][SRC], colors[1][SRC]), colors[2][SRC]);
		Arrays.fill(isVisited, false);
		
		for(int i = 0; i < SIZE; ++i) {
			if(max == colors[i][SRC]) {
				bulbs[SRC] = i;
				for(int v : adj[SRC]) makeBulbColor(i, v);
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		for(int i = 1; i <= N; ++i) {
			for(int c = 0; c < SIZE; ++c) {
				colors[c][i] = readInt();
			}
		}
		solution();
		
		sb.append(max).append("\n");
		for(int i = 1; i <= N; ++i) sb.append(COLOR_TABLE[bulbs[i]]);
		System.out.println(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}