import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Lecture implements Comparable<Lecture> {
		int no, begin, end;
		
		Lecture(int no, int beg, int end) {this.no = no; this.begin = beg; this.end = end;}

		@Override
		public int compareTo(Lecture other) { // 강의 시작 시각 순으로 정렬
			return this.begin - other.begin;
		}
	};
	static class Room implements Comparable<Room> {
		int no, end;
		
		Room(int no, int end) {this.no = no; this.end = end;}

		@Override
		public int compareTo(Room other) { // 강의 종료 시각 순으로 정렬
			return this.end - other.end;
		}
	};
	// constants
	// variables
	static int N, M;
	static PriorityQueue<Lecture> pqBeginTime = new PriorityQueue<>();
	static PriorityQueue<Room> pqEndTime = new PriorityQueue<>();
	static boolean[] rooms = new boolean[200_001];
	
	
	static void solution() {
		while(!pqBeginTime.isEmpty()) { // 강의를 순서대로 진행
			Lecture lecture = pqBeginTime.poll();
			// 현재 넣으려는 강의의 시작 시점에 끝나는 강의를 모두 제거
			while(!pqEndTime.isEmpty() && pqEndTime.peek().end <= lecture.begin) {
				rooms[pqEndTime.poll().no] = false;
			}
			if(!rooms[lecture.no]) {
				rooms[lecture.no] = true;
				pqEndTime.offer(new Room(lecture.no, lecture.end));
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i < M; ++i) {
			pqBeginTime.offer(new Lecture(readInt(), readInt(), readInt()));
		}
		solution();
		System.out.print(sb);
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