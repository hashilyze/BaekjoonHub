import java.io.*;
import java.util.*;


public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Travel implements Comparable<Travel> {
		int from, to;
		int term;

		public Travel(int from, int to) {
			this.from = from;
			this.to = to;
			this.term = from - 1; // 초기화: 다른 여행을 가지 않음 -> 1 ~ 여행가기 전날
		}

		@Override
		public int compareTo(Travel other) {
			if(this.from != other.from) return this.from - other.from;
			return this.to - other.to; 
		}

		@Override
		public String toString() {
			return "Travel [from=" + from + ", to=" + to + ", term=" + term + "]";
		}
	}
	// constants
	// variables
	static int N, M;
	static Travel[] travels;
	
	static int solution() {
		// 여행을 마치는 일자 순으로 정렬
		Arrays.sort(travels);
		
		int min = N; // 여행을 하지 않는 최소 기간의 최댓값 -> 모든 여행을 가지 않음
		for(int i = 0; i < M; ++i) {
			Travel target = travels[i];
			
			for(int j = 0; j < M; ++j) { // 이전에 갈 수 있는 다른 여행을 탐색
				Travel prev = travels[j];
				if(prev.to >= target.from) continue;
				// 여행을 가지 않는 기간 = (이전 여행 일 + 1) ~ (이번 여행 일 - 1) 
				target.term = Math.min(target.term, Math.max(prev.term, target.from - prev.to - 1));
			}
			// 마지막 여행 이후 여행을 가지 않는 기간 = (마지막 여행 일+1) ~ 기간 종료일
			min = Math.min(min, Math.max(target.term, N - target.to));
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