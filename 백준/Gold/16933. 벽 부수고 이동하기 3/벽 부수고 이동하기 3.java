import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node {
		static final int eDAY = 1, eNIGHT = 0;
		
		int y, x, wall, dist;

		public Node(int y, int x, int wall, int dist) {
			this.y = y;
			this.x = x;
			this.wall = wall;
			this.dist = dist;
		}
	}
	// constants
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	// variables
	static int N, M, K;
	static char[][] map;
	static int[][] isVisited;
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M; 
	}
	
	static int solution() {
		Deque<Node> q = new ArrayDeque<>();
		q.offerLast(new Node(0, 0, 0, 1));
		isVisited[0][0] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.pollFirst();
			if(node.y == N - 1 && node.x == M - 1) 
				return node.dist;
			
			for(int d = 0; d < dy.length; ++d) {
				int ny = dy[d] + node.y;
				int nx = dx[d] + node.x;
				
				if(!inRange(ny, nx)) continue;
				if(isVisited[ny][nx] <= node.wall) continue;
				
				if(map[ny][nx] == '1') { // 벽을 부숴야 이동할 수 있다.
					// 이미 벽을 K번 부쉈거나, 더 적은 횟수로 벽을 부수면서 더 빠르게 도달한 방법이 존재한다.(벽을 부술 수 있는 횟수가 많이 남아 있을 수록 빠르게 도달할 가능성이 높다.)
					if(node.wall == K || isVisited[ny][nx] <= node.wall + 1) {
						continue;
					}
					
					if((node.dist & 1) == Node.eNIGHT) { // 밤이면 부술 수 없다. (대기)
						q.offerLast(new Node(node.y, node.x, node.wall, node.dist + 1));
					} else { // 낮이면, 벽을 부수고 이동한다.
						q.offerLast(new Node(ny, nx, (isVisited[ny][nx] = node.wall + 1), node.dist + 1));						
					}
				} else {
					q.offerLast(new Node(ny, nx, (isVisited[ny][nx] = node.wall), node.dist + 1));
				}
			}
			
		}
		return -1; 
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		K = readInt();
		
		map = new char[N][];
		isVisited = new int[N][M];
		
		for(int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(isVisited[i], Integer.MAX_VALUE);
		}
		
		System.out.print(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}