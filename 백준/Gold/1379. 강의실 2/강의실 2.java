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
		public int compareTo(Lecture other) { // 강의 시작시간 순으로 정렬
			return this.begin - other.begin;
		}
	};
	static class Room implements Comparable<Room> {
		int room, end;
		
		Room(int room, int end) {this.room = room; this.end = end;}

		@Override
		public int compareTo(Room other) { // 강의가 끝나는 순으로 정렬
			return this.end - other.end;
		}
	};
	// constants
	// variables
	static int N;
	static PriorityQueue<Lecture> pqBeginTime = new PriorityQueue<>();
	static PriorityQueue<Room> pqEndTime = new PriorityQueue<>();
	static PriorityQueue<Integer> watingRooms = new PriorityQueue<Integer>();
	static int[] rooms = new int[100_000];
	
	
	static int solution() {
		for(int i = 0; i < N; ++i) watingRooms.offer(i + 1);
		
		int max = 0;
		while(!pqBeginTime.isEmpty()) { // 강의를 순서대로 진행
			Lecture lecture = pqBeginTime.poll();
			// 현재 넣으려는 강의의 시작 시점에 끝나는 강의를 모두 제거
			while(!pqEndTime.isEmpty() && pqEndTime.peek().end <= lecture.begin) {
				watingRooms.offer(pqEndTime.poll().room); // 빈 강의실을 큐에 대기
			}
			rooms[lecture.no - 1] = watingRooms.poll(); // 빈 강의실 중 가장 번호가 작은 것을 할당 
			pqEndTime.offer(new Room(rooms[lecture.no - 1], lecture.end));
			
			// 현재 진행 중인 강의의 수로 최댓값 갱신
			max = Math.max(max, pqEndTime.size()); 
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			pqBeginTime.offer(new Lecture(readInt(), readInt(), readInt()));
		}
		sb.append(solution()).append("\n");
		for(int i = 0; i < N; ++i) sb.append(rooms[i]).append("\n");
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