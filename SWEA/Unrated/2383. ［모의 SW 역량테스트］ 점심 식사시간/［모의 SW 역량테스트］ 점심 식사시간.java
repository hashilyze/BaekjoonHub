import java.io.*;
import java.util.*;

public class Solution {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Task implements Comparable<Task> {
		int time; // 작업 발생 시각
		int type; // 0: 계단을 나감; 1: 계단으로 이동

		public Task(int time, int type) {this.time = time;this.type = type;}

		@Override
		public int compareTo(Task o) { // 빠른 작업 순; 나가는 작업이 우선
			if(this.time != o.time) return this.time - o.time; 
			return this.type - o.type;
		}
		
		@Override
		public String toString() { // 디버깅용 
			return "Task [time=" + time + ", type=" + type + "]";
		}
	}
	// constants
	static final int STAIR_SIZE = 2; 	// 계단의 수
	static final int PEOPLE_SIZE = 10; 	// 사람의 최대 수 
	static final int MAX_HOLE = 3; 		// 동시에 계단을 이용할 수 있는 사람의 최대 수 -> 가상의 자원 단위 '통로'
	static final int EXIT_STAIR = 0;	// 통로를 이용하던 사람이 통로를 반환하는 작업
	static final int ENTER_STAIR = 1;	// 계단에 도착한 사람이 계단에 통로를 요청하는 작업
	// variables
	static int N;
	static int stairCnt = 0;  // 계단의 수 (2로 고정)
	static int[][] stairs = new int[STAIR_SIZE][2];  // 계단의 좌표
	static int[] stairDistance = new int[2]; // 계단을 내려가는데 걸리는 시간
	static int peopleCnt = 0; // 사람의 수
	static int[][] people = new int[PEOPLE_SIZE][2]; // 사람의 좌표
	
	
	// 맨헤튼 거리
	static int distance(int x1, int y1, int x2, int y2) { return Math.abs(x1 - x2) + Math.abs(y1 - y2);  }
	
	static int simulate(int bitMask, int stairId) {
		int t = 0;
		PriorityQueue<Task> pq = new PriorityQueue<>(); // 작업이 발생하는 시각을 저장
		int cntWaiting = 0;		// 대기 중인 사람의 수
		int cntIdle = MAX_HOLE; // 사용되지 않고 있는 통로의 수
		
		for(int i = 0; i < peopleCnt; ++i) { // 계단을 이용할 사람을 수집
			if(((bitMask >> i) & 1) == stairId) {
				int dist = distance(stairs[stairId][0], stairs[stairId][1], people[i][0], people[i][1]);
				pq.offer(new Task(dist, 1));
			}
		}
		while(!pq.isEmpty()) { // 모든 사람이 계단을 내려가면 작업이 끝남
			Task task = pq.poll(); // 작업이 발생하는 시점으로 시간 경과
			t = task.time;
			
			if(task.type == 0) { // Exit
				if(cntWaiting == 0) { // 대기 중인 사람이 없음
					++cntIdle;
				} else { // 대기 중인 사람에게 통로를 전달
					pq.offer(new Task(t + stairDistance[stairId], 0));
					--cntWaiting;
				}
			} else { // (task.type == 1); Enter
				if(cntIdle > 0) { // 현재 사용되지 않는 통로를 획득; 
					--cntIdle;
					pq.offer(new Task(t + stairDistance[stairId] + 1, 0));
				} else { // 대기
					++cntWaiting;
				}
			}
		}
		return t;  
	}
	
	static int solution() {
		int min = Integer.MAX_VALUE;
		// 사람들이 계단을 선택하는 모든 경우에 수에 대해 최소 시간 탐색
		min = Math.min(min, simulate(0x00, 0));
		for(int bitMask = 0x01, limit = (0x01 << peopleCnt) - 1; bitMask < limit; ++bitMask) {
			// 모든 사람이 계단을 내려가는 시간
			// (계단 1을 내려가려는 사람이 모두 내려간 시간)과 (계단 2를 내려가려는 사람이 모두 내려간 시간) 중 더 긴 시간 
			min = Math.min(min, Math.max(simulate(bitMask, 0), simulate(bitMask, 1)));
		}
		min = Math.min(min, simulate((0x01 << peopleCnt) - 1, 1));
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			stairCnt = 0;
			peopleCnt = 0;
			
			N = readInt();
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					int v = readInt();
					if(v == 1) {
						people[peopleCnt][0] = x;
						people[peopleCnt][1] = y;
						++peopleCnt;
					} else if(v >= 2){
						stairs[stairCnt][0] = x;
						stairs[stairCnt][1] = y;
						stairDistance[stairCnt] = v;
						++stairCnt;
					}
				}
			}
			sb.append('#').append(t).append(' ').append(solution()).append('\n');
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
