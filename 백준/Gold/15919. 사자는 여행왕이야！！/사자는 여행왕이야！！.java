import java.io.*;
import java.util.*;


public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Travel {
		int from, to;
		int term;

		public Travel(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	// constants
	// variables
	static int N, M;
	static Travel[] travels;
	
	static int solution() {
		// 여행을 마치는 일자 순으로 정렬
		Arrays.sort(travels, (lhs, rhs)->lhs.to != rhs.to ? lhs.to - rhs.to : lhs.from - rhs.from);
		// 여행을 시작하는 일자 순으로 정렬
		PriorityQueue<Travel> pq = new PriorityQueue<>((lhs, rhs)->lhs.from != rhs.from ? lhs.from - rhs.from : lhs.to - rhs.to);
		for(int i = 0; i < M; ++i) pq.offer(travels[i]);
		
		while(!pq.isEmpty()) {
			Travel target = pq.poll();
			target.term = target.from - 1; // 초기화: 다른 여행을 가지 않음 -> 1 ~ 여행가기 전날
			for(int i = 0; i < M && travels[i].to < target.from; ++i) { // 이전에 갈 수 있는 다른 여행을 탐색
				target.term = Math.min(target.term, Math.max(travels[i].term, target.from - travels[i].to - 1));
			}
		}
		
		int min = N; // 여행을 하지 않는 최소 기간
		for(Travel travel : travels) {
			min = Math.min(min, Math.max(travel.term, N - travel.to));
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		travels = new Travel[M];
		for(int i = 0; i < M; ++i) travels[i] = new Travel(readInt(), readInt());		
		System.out.print(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}