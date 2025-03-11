import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	// variables
	static long N;
	static Set<Long> isVisited = new HashSet<>();
	
	
	static long solution() {
		// N에서 1을 만드는 최단 경로 찾기
		Deque<long[]> q = new ArrayDeque<>();
		q.add(new long[] {N, 0});
		isVisited.add(N);
		
		while(!q.isEmpty()) {
			long[] u = q.pollFirst();
			long v = u[0], d = u[1];
			if(v == 1L) return d;
			
			if(v % 3 == 0 && !isVisited.contains(v / 3)) {
				isVisited.add(v / 3);
				q.offerLast(new long[] {v / 3, d + 1});
			}
			if(v % 2 == 0 && !isVisited.contains(v / 2)) {
				isVisited.add(v / 2);
				q.offerLast(new long[] {v / 2, d + 1});
			}
			if(!isVisited.contains(v - 1)) {
				isVisited.add(v - 1);
				q.offerLast(new long[] {v - 1, d + 1});
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		N = readLong();
		System.out.println(solution());
	}
	
	static long readLong() throws IOException {
		long c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
