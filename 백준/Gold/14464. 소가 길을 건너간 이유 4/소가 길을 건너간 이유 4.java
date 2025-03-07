import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static class Cow implements Comparable<Cow>{
		int begin, end;
		Cow() { }
		Cow(int beg, int end) { this.begin = beg; this.end = end; }
		
		@Override
		public int compareTo(Cow other) { // 시작 시간이 빠른 순; 종료 시간이 빠른 순
			if(this.begin != other.begin) return this.begin - other.begin; 
			return this.end - other.end;
		};
	};
	static final int SIZE = 20_000;
	static int C, N;
	static int[] chickens = new int[SIZE];
	static Cow[] cows = new Cow[SIZE];
	
	
	static int solution() {
		Arrays.sort(chickens, 0, C);
		Arrays.sort(cows, 0, N);
		
		/**
		 * 전략:
		 * 1. 각 닭은 가능한 소 중에서 마감기한이 임박한 소를 선택한다. 
		 */
		PriorityQueue<Integer> pq = new PriorityQueue<>(); 
		int cnt = 0;
		for(int i = 0, j = 0; i < C; ++i) { 
			int chicken = chickens[i];
			while(j < N && cows[j].begin <= chicken) pq.offer(cows[j++].end); // 닭이 도착하기 전에 시작한 소를 후보에 등록
			while(!pq.isEmpty() && pq.peek() < chicken) pq.poll(); // 닭이 도착한 시점에 종료한 소를 후보에서 제거
			if(!pq.isEmpty()) { // 후보가 존재한다면 옮김
				pq.poll();
				++cnt;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		C = readInt();
		N = readInt();
		for(int i = 0; i < C; ++i) { chickens[i] = readInt(); }
		for(int i = 0; i < N; ++i) { cows[i] = new Cow(readInt(), readInt()); }
		System.out.println(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}