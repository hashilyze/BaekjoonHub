import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static class Node{
		int y, x;
		int length;
		int id;
		
		public Node() {}
		public Node(int y, int x, int length, int id) {
			this.y = y;
			this.x = x;
			this.length = length;
			this.id = id;
		}
	};
	
	
	static final int[][] DELTA = {
			{-1, 0}, {1, 0}, {0, -1}, {0, 1}
	};
	
	static int N;
	static int[][] mat;
	
	static boolean isInOfRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
	
	static void floodFill(int ay, int ax, int flag) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {ax, ay});
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			int x = u[0], y = u[1];
			
			if(mat[y][x] >= 0) continue;
			mat[y][x] = flag;
			
			for(int i = 0; i < DELTA.length; ++i) {
				int ny = y + DELTA[i][1];
				int nx = x + DELTA[i][0];
				if(isInOfRange(ny, nx)) {
					q.add(new int[] {nx, ny});
				}				
			}
		}
	}
	
	static int solution(){
		// 1. 섬을 구분한다.
		int id = 1;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(mat[y][x] < 0) floodFill(y, x, id++);
			}
		}
		// 2. 다리를 연결한다.
		Deque<Node> qOrg = new ArrayDeque<>();
		Deque<Node> qNext = new ArrayDeque<>();
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(mat[y][x] > 0) {
					qNext.add(new Node(y, x, 0, mat[y][x]));
				}
			}
		}
		Node[][] isVisited = new Node[N][N];
		while(!qNext.isEmpty()) {
			Deque<Node> tmp = qOrg; qOrg = qNext; qNext = tmp;
			int minLnegth = Integer.MAX_VALUE;
			
			while(!qOrg.isEmpty()) {
				Node u = qOrg.pollFirst();
				
				if(isVisited[u.y][u.x] != null) {
					Node v = isVisited[u.y][u.x]; 
					if(v.id == u.id) continue;
					minLnegth = Math.min(minLnegth, v.length + u.length - 1);
				}
				isVisited[u.y][u.x] = u;
				
				for(int i = 0; i < DELTA.length; ++i) {
					int ny = u.y + DELTA[i][1];
					int nx = u.x + DELTA[i][0];
					if(isInOfRange(ny, nx)) {
						qNext.add(new Node(ny, nx, u.length + 1, u.id));
					}				
				}
			}
			
			if(minLnegth < Integer.MAX_VALUE) return minLnegth;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		
		for(int y = 0; y < N; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; ++x) {
				mat[y][x] = -Integer.parseInt(st.nextToken());
			}
		}
		int result = solution();
		bw.append(result + "");
		bw.flush();
			
	}

}
