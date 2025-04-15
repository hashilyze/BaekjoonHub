import java.io.*;
import java.util.*;

/**
 * 메모리: KB
 * 시간: ms
 * @author 
 *
 */
public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Event implements Comparable<Event>{
		int life, y, x, time, type;
		public Event() { }
		public Event(int life, int y, int x, int time, int type) {
			this.life = life;
			this.y = y;
			this.x = x;
			this.time = time;
			this.type = type;
		}

		@Override
		public int compareTo(Event o) { 
			if(this.time != o.time) return this.time-o.time; // 시간이 빠른 순
			if(this.type != o.type) return this.type-o.type; // 이벤트 우선순위가 높은 순; 활성->번식->사망
			return o.life-this.life; // 생명력이 높은 순
		}
	}
	// constants
	static final int MAX_N = 50;
	static final int MAX_M = 50;
	static final int MAX_K = 300;
	static final int SIZE = (MAX_K + MAX_K + MAX_N + 10);
	static final int[][] DELTA = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static final int ESLEEP = 0, EALIVE = 1, EREPRODUCT = 2, EDEAD = 3;
	// variables
	static int N, M, K;
	static int[][] mat = new int[SIZE][SIZE];
	static int sleep, alive, dead;
	static PriorityQueue<Event> pq = new PriorityQueue<>();
	
	static void makeCell(int time, int life, int y, int x) {
		pq.offer(new Event(life, y, x, time + life, EALIVE)); // 활성화
		pq.offer(new Event(life, y, x, time + life + 1, EREPRODUCT)); // 번식
		pq.offer(new Event(life, y, x, time + (life << 1), EDEAD)); // 사망
		++sleep;
	}
	static void aliveCell(Event e) {
		--sleep;
		++alive;
	}
	static void reproductCell(Event e) {
		for(int d = 0; d < DELTA.length; ++d) {
			int nx = e.x + DELTA[d][0];
			int ny = e.y + DELTA[d][1];
			if(mat[ny][nx] == 0) {
				mat[ny][nx] = e.life;
				makeCell(e.time, e.life, ny, nx);
			}
		}
	}
	static void deadCell(Event e) {
		--alive;
		++dead;
	}
	
	static int solution() {
		for(int t = 1; t <= K; ++t) {
			while(!pq.isEmpty() && pq.peek().time <= t) {
				Event event = pq.poll();
				switch(event.type) {
				case ESLEEP: break; // 초기 상태
				case EALIVE: aliveCell(event); break; // X시간 후 활성화
				case EREPRODUCT: reproductCell(event); break; // X+1시간 후 번식
				case EDEAD: deadCell(event); break; // 2*X 시간 후 사망
				}
			}
		}
		return alive + sleep;
	}
	
	/* 
	 * 생명력 X에 대해, 시간 T에 생성된 세포는 T+X에 활성화되고, T+X+1에 번식, T+2*X에 비활성화된다.
	 * 번식은 사방으로 한다.
	 * 번식하려는 위치에 이미 세포가 존재하면, 번식할 수 없다.
	 * 동일한 시간에 같은 위치에 여러 세포가 번식하면, 생명력이 가장 큰 세포가 번식된다.
	 */
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			// 초기화
			sleep = alive = dead = 0;
			pq.clear();
			for(int y = 0; y < mat.length; ++y) Arrays.fill(mat[y], 0);
			
			// Input
			N = readInt(); M = readInt(); K = readInt();
			
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < M; ++x) {
					int py = y + MAX_K, px = x + MAX_K;
					int life = readInt();
					
					if((mat[py][px] = life) != 0) { // 세포가 존재
						makeCell(0, life, py, px);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}