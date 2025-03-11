import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node{
		int v, w;
		Node(int v, int w) {this.v = v; this.w = w;}
	}
	// constants
	static final int SIZE = 10_000;
	// variables
	static int N;
	static List<Node>[] adj = new List[SIZE + 1];
	
	
	static int[] dfs(int u) {
		if(adj[u].isEmpty()) return new int[] {0, 0};
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		int maxPath = 0; 	// 해당 정점에서 리프로 내려가는 가장 긴 경로
		int maxSubTree = 0; // 해당 정점을 루트로 하는 서브트리에서 임의의 두 정점을 잇는 가장 긴 경로 -> 리프에서 리프 or 리프에서 루트
		for(Node next : adj[u]) {
			int[] ret = dfs(next.v);
			
			int path = ret[0] + next.w;
			maxPath = Math.max(maxPath, path);  
			maxHeap.offer(path);
			
			maxSubTree = Math.max(maxSubTree, Math.max(path, ret[1]));
		}
		if(maxHeap.size() >= 2) {
			maxSubTree = Math.max(maxSubTree, maxHeap.poll() + maxHeap.poll());
		}
		return new int[] {maxPath, maxSubTree};
	}
	
	static int solution() {
		int[] ans = dfs(1);
		return ans[1];
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<Node>();
		for(int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt(), w = readInt();
			adj[u].add(new Node(v, w));
		}
		System.out.println(solution());
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