import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	static class Edge{
		int s, e, w;
		public Edge(int s, int e, int w) { this.s = s; this.e = e; this.w = w; }
	}
	// constants
	static int MAX_N = 500;
	static int MAX_M = 2_500;
	static int MAX_W = 200;
	// variables
	static int N, M, W;
	static Edge[] edges = new Edge[2*MAX_M + MAX_W];
	static int[] distances = new int[MAX_N + 1];
	
	
	static boolean solution() {
		Arrays.fill(distances, 1, N + 1, Integer.MAX_VALUE);
		for(int v = 1; v <= N; ++v) {
			if(distances[v] != Integer.MAX_VALUE) continue;
			distances[v] = 0;
			
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < 2*M + W; ++j) {
					Edge edge = edges[j];
					if(distances[edge.s] != Integer.MAX_VALUE && distances[edge.e] > distances[edge.s] + edge.w) {
						distances[edge.e] = distances[edge.s] + edge.w;
						if(i == N - 1) return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			N = readInt();
			M = readInt();
			W = readInt();
			
			for(int i = 0; i < M; ++i) {
				int s = readInt(), e = readInt(), w = readInt();
				edges[i] = new Edge(s, e, w);
				edges[i + M] = new Edge(e, s, w);
			}
			for(int i = 0; i < W; ++i) edges[2*M + i] = new Edge(readInt(), readInt(), -readInt());
			sb.append(solution() ? "YES\n" : "NO\n");
		}
		System.out.println(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}