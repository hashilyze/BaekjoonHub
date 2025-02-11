import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static int N, M;
	static List<List<Integer>> adj = new ArrayList<>();
	static boolean[] isVisited;
	
	static int retCur;
	static int[] result = new int[32_000];
	
	
	static void dfs(int at) {
		if(isVisited[at]) return;
		isVisited[at] = true;
		
		for(int next : adj.get(at)) {
			dfs(next);
		}
		result[--retCur] = at;
	}
	
	static void solution() {
		for(int i = 0; i < N; ++i) {
			dfs(i);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; ++i) {
			adj.add(new ArrayList<>());
		}
		isVisited = new boolean[N];
		retCur = N;
		
		
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adj.get(from).add(to);
		}
		
		// solution
		solution();
		
		// output
		for(int i = 0; i < N; ++i) {
			bw.append(result[i] + 1 + " ");
		}
		bw.flush();
	}
}
