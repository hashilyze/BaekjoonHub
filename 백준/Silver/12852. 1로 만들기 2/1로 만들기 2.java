import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static int[] isVisited = new int[1_000_001]; // isVisited[i]: i의 방문여부 및 N에서 1로 향하는 역방향 경로를 저장
	
	
	static void solution() {
		// N에서 1을 만드는 최단 경로 찾기
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {N, 0});
		isVisited[N] = N;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int v = u[0], d = u[1];
			if(v == 1) break;
			
			if(v % 3 == 0 && isVisited[v / 3] == 0) {
				isVisited[v / 3] = v;
				q.offerLast(new int[] {v / 3, d + 1});
			}
			if(v % 2 == 0 && isVisited[v / 2] == 0) {
				isVisited[v / 2] = v;
				q.offerLast(new int[] {v / 2, d + 1});
			}
			if(isVisited[v - 1] == 0) {
				isVisited[v - 1] = v;
				q.offerLast(new int[] {v - 1, d + 1});
			}
		}
		
		// 역방향 경로를 정방향을 복원
		Deque<Integer> stk = new ArrayDeque<>();
		int u = 1;
		while(u != N) {
			stk.offerLast(u);
			u = isVisited[u];
		}
		stk.offerLast(u);
		
		sb.append(stk.size() - 1).append("\n");
		while(!stk.isEmpty()) {
			sb.append(stk.pollLast()).append(" ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		solution();
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
