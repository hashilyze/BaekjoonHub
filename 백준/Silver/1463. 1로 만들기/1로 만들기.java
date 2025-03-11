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
	static boolean[] isVisited = new boolean[1_000_001];
	
	
	static int solution() {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {N, 0});
		isVisited[N] = true;
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int v = u[0], d = u[1];
			if(v == 1) return d;
			
			if(v % 3 == 0 && !isVisited[v / 3]) {
				isVisited[v / 3] = true;
				q.offerLast(new int[] {v / 3, d + 1});
			}
			if(v % 2 == 0 && !isVisited[v / 2]) {
				isVisited[v / 2] = true;
				q.offerLast(new int[] {v / 2, d + 1});
			}
			if(!isVisited[v - 1]) {
				isVisited[v - 1] = true;
				q.offerLast(new int[] {v - 1, d + 1});
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		System.out.println(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
