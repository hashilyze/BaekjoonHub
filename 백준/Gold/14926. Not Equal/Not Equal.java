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
	static int N;
	static boolean[][] isVisited;
	static int top;
	static int[] euler;
	
	static void eulerPath(int u) {
		for(int v = 0; v < N; ++v) {
			if(isVisited[u][v]) continue;
			isVisited[u][v] = isVisited[v][u] = true;
			eulerPath(v);
		}
		euler[top--] = u;
	} 
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		isVisited = new boolean[N][N];
		for(int u = 0; u < N; ++u) isVisited[u][u] = true;
		top = N * (N - 1) >> 1;
		euler = new int[top+1];
		
		eulerPath(0);
		for(int v : euler) {
			sb.append("a").append(v+1).append(" ");
		}
		System.out.print(sb);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}