import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Lecture implements Comparable<Lecture>{
		int type;
		int time;
		
		Lecture(){ }
		Lecture(int type, int time){ this.type = type; this.time = time; }
		
		@Override
		public int compareTo(Lecture other) { // 시간의 오름차순; 유형 순 정렬
			if(this.time != other.time) return this.time - other.time;
			return this.type - other.type;
		}
	};
	// constants
	static final int SIZE = 200_000;
	// variables
	static int N;
	static Lecture[] lectures = new Lecture[SIZE << 1];
	
	static int solution() {
		Arrays.sort(lectures, 0, N << 1);
		
		int max = 0, cnt = 0;
		for(int i = 0; i < (N << 1); ++i) {
			if(lectures[i].type > 0) ++cnt;
			else max = Math.max(max, cnt--);
		}
		return max;
	}
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			// 동일한 시점에서 한 강의가 끝나고 다른 강의가 시작하면 강의실은 하나만 필요 -> 겹치지 않음
			lectures[i] = new Lecture(1, readInt()); // 강의 시작 시간
			lectures[i + N] = new Lecture(-1, readInt()); // 강의 종료 시간
		}
		System.out.println(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}