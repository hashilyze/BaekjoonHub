import java.io.*;
import java.util.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static final int MAX_N = 500;
	static int N, M;
	static List<Integer>[] adj = new List[MAX_N];
	static List<Integer>[] reverseAdj = new List[MAX_N];
    static boolean[] isVisited = new boolean[MAX_N];
	
	static int countNodes(int s, List<Integer>[] adj) {
		Arrays.fill(isVisited, false);
        
        int cnt = -1;
		Deque<Integer> q = new ArrayDeque<>();
		q.offerLast(s);
        isVisited[s] = true;
        ++cnt;
        
		while(!q.isEmpty()) {
			int u = q.pollFirst();
			
			for(int v : adj[u]) {
                if(isVisited[v]) continue;
                isVisited[v] = true;
                ++cnt;
                
				q.offerLast(v);
			}
		}
		return cnt;
	}
	
	static int solution() {
		int cnt = 0;
		// 자신 보다 위에 있는 학생의 수 + 자신보다 밑에 있는 학생의 수 == N - 1 인 경우에만, 정확히 파악 가능
		for(int u = 0; u < N; ++u) {
			int cntUpper = countNodes(u, adj);
			int cntLower = countNodes(u, reverseAdj);
			if(cntUpper + cntLower == N - 1) {
				++cnt;
			}
		}
		return cnt;
	}
	
	public static void main(String args[]) throws Exception {
		// Inputs
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < N; ++i) reverseAdj[i] = new ArrayList<>();
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
			reverseAdj[v].add(u);
		}
		
		// Output
		bw.append(solution() + "").flush();
	}
}