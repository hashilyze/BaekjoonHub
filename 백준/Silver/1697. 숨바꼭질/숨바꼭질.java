import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		int t, x;
		Node(int t, int x) {this.t = t; this.x = x;}
	};
	// constants
	static final int MAX_N = 100_000;
	// variables
	static int N, K;
	static int[] isVisited = new int[MAX_N + 10];
	
	
	static boolean isValid(int x) {
		return 0 <= x && x <= MAX_N + 1 && isVisited[x] == 0;
	}
	
	static int solution() {
		Deque<Integer> q = new ArrayDeque<>();
		q.offerLast(N);
		isVisited[N] = 0;
		
		while(!q.isEmpty()) {
			int u = q.pollFirst();
			if(u == K) return isVisited[K];
			
			if(isValid(u - 1)) {
				isVisited[u - 1] = isVisited[u] + 1;
				q.offerLast(u - 1);
			}
			if(isValid(u + 1)) {
				isVisited[u + 1] = isVisited[u] + 1;
				q.offerLast(u + 1);
			}
			if(isValid(u << 1)) {
				isVisited[u << 1] = isVisited[u] + 1;
				q.offerLast(u << 1);
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(solution());
	}
}
