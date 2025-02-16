import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// types
	static class Vector{
		int y, x;
		int dist;
		
		public Vector() { }
		public Vector(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
	// constants
	static int[][] DELTA = new int[][] {
		{0, -1}, {-1, 0}, {1, 0}, {0, 1}
	};
	// variables
	static int N;
	static int[][] mat;
	static int[] src;

	
	static boolean isInOfRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static Vector findFish(int ay, int ax, int lv) {
		boolean[][] isVisited = new boolean[N][N];
		
		Deque<Vector> q = new ArrayDeque<Vector>();
		q.add(new Vector (ay, ax, 0));
		List<Vector> li = new ArrayList<Vector>();
		
		while(!q.isEmpty()) {
			Vector u = q.pollFirst();
			int y = u.y, x = u.x, dist = u.dist; 

			if(isVisited[y][x]) continue;
			isVisited[y][x] = true;
			if(!li.isEmpty() && li.get(0).dist < dist) continue; // short-check
			
			if(0 < mat[y][x] && mat[y][x] < lv) { // 크기가 작은 물고기만 먹음
				li.add(u);
			} else if(mat[y][x] == 0 || mat[y][x] == lv) { // 빈칸과 같은 크기는 지나감
				for(int d = 0; d < DELTA.length; ++d) {
					int ny = y + DELTA[d][1];
					int nx = x + DELTA[d][0];
					if(isInOfRange(ny, nx)) {
						q.add(new Vector(ny, nx, dist + 1));
					}
				}
			}
		}
		
		if(li.isEmpty()) {
			return null;
		} else {
			li.sort((lhs, rhs)->{
				if(lhs.dist != rhs.dist) return lhs.dist - rhs.dist;
				else if(lhs.y != rhs.y) return lhs.y - rhs.y;
				return lhs.x - rhs.x;
			});
			return li.get(0);
		}
	}
	
	static int solution() {
		int t = 0; 		// 경과 시간 (최댓값: 20*20*20)
		int lv = 2; 	// 아기 상어의 크기
		int exp = 0;	// 다음 크기로 성장하기 위해 먹은 물고기의 수
		
		mat[src[1]][src[0]] = 0;
		log(t);
		while(true) {
			Vector target = findFish(src[1], src[0], lv);
			if(target == null) 
				return t;
			
			t += target.dist;
			src = new int[] {target.x, target.y};
			mat[src[1]][src[0]] = 0;
			
			if(++exp == lv) {
				++lv;
				exp = 0;
			}
			log(t);
		}
	}
	static int sim = 0;
	static void log(int t) {
//		mat[src[1]][src[0]] = 9;
//		System.out.println("===" + sim++ + "===: " + t);
//		for(int y = 0; y < N; ++y) {
//			for(int x = 0; x < N; ++x) {
//				System.out.print(mat[y][x] + " ");
//			}
//			System.out.println();
//		}
//		mat[src[1]][src[0]] = 0;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; ++x) {
				int v = Integer.parseInt(st.nextToken());
				if((mat[y][x] = v) == 9) {
					src = new int[] {x, y};
				}
			}
		}
		bw.append(solution()+"").flush();
	}
}
	