import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Lecture implements Comparable<Lecture> {
		int begin, end;
		
		Lecture(int beg, int end) {this.begin = beg; this.end = end;}

		@Override
		public int compareTo(Lecture other) { // 강의 시작 시각 순으로 정렬
			return this.begin - other.begin;
		}
	};
	// constants
	// variables
	static int N;
	static PriorityQueue<Lecture> pqBeginTime = new PriorityQueue<>();
	static PriorityQueue<Integer> pqEndTime = new PriorityQueue<>();
	
	
	static int solution() {
		int max = 0;
		while(!pqBeginTime.isEmpty()) { // 강의를 순서대로 진행
			Lecture lecture = pqBeginTime.poll();
			// 현재 넣으려는 강의의 시작 시점에 끝나는 강의를 모두 제거
			while(!pqEndTime.isEmpty() && pqEndTime.peek() <= lecture.begin) {
				pqEndTime.poll();
			} 
			pqEndTime.offer(lecture.end);
			
			// 현재 진행 중인 강의의 수로 최댓값 갱신
			max = Math.max(max, pqEndTime.size()); 
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			pqBeginTime.offer(new Lecture(readInt(), readInt()));
		}
		System.out.print(solution());
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