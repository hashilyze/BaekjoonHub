import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, K;
	static Deque<Integer> q = new ArrayDeque<Integer>();
	
	
	static void solution() {
		for(int i = 1; i <= N; ++i) q.offerLast(i);
		
		sb.append("<");
		while(!q.isEmpty()) {
			for(int i = 0; i < K - 1; ++i) {
				q.offerLast(q.pollFirst());
			}
			sb.append(q.pollFirst());
			sb.append(q.isEmpty() ? ">" : ", ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		solution();
		
		// Output
		System.out.print(sb);
	}
}