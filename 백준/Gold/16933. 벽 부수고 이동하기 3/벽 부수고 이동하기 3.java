import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node {
		static final int eDAY = 0, eNIGHT = 1;
		
		int y, x, day, wall, dist;

		public Node(int y, int x, int day, int wall, int dist) {
			this.y = y;
			this.x = x;
			this.day = day;
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
	static boolean[][][][] isVisited;
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M; 
	}
	
	static int solution() {
		Deque<Node> q = new ArrayDeque<>();
		q.offerLast(new Node(0, 0, Node.eDAY, 0, 1));
		isVisited[0][0][Node.eDAY][0] = true;
		
		while(!q.isEmpty()) {
			Node node = q.pollFirst();
			if(node.y == N - 1 && node.x == M - 1) return node.dist;
			
			if(!isVisited[node.y][node.x][node.day^1][node.wall]) { // 제자리에서 대기 한다.
				q.offerLast(new Node(node.y, node.x, node.day^1, node.wall, node.dist + 1));
				isVisited[node.y][node.x][node.day^1][node.wall] = true;
			}
			
			for(int d = 0; d < dy.length; ++d) {
				int ny = dy[d] + node.y;
				int nx = dx[d] + node.x;
				
				if(!inRange(ny, nx)) continue;
				
				if(map[ny][nx] == '1') { // 벽을 부숴야 이동할 수 있다.
					// 밤이거나, 벽을 이미 K번 부쉈다면 이동할 수 없다.
					if(node.day == Node.eNIGHT || node.wall == K) {
						continue;
					}
					if(isVisited[ny][nx][node.day^1][node.wall+1]) continue;
					
					q.offerLast(new Node(ny, nx, node.day^1, node.wall + 1, node.dist + 1));
					isVisited[ny][nx][node.day^1][node.wall+1] = true;
				} else {
					// 이미 동일한 조건으로 방문한 칸
					if(isVisited[ny][nx][node.day^1][node.wall]) continue;
					
					q.offerLast(new Node(ny, nx, node.day^1, node.wall, node.dist + 1));
					isVisited[ny][nx][node.day^1][node.wall] = true;
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
		for(int i = 0; i < N; ++i) map[i] = br.readLine().toCharArray();
		
		isVisited = new boolean[N][M][2][K + 1];
		
		System.out.print(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}