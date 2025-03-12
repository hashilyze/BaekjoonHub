import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1000;
	// variables
	static int N, M;
	static char[][] mat = new char[SIZE][];
	static int[][] isVisited = new int[SIZE][SIZE];
	
	
	static int findCycle(int ay, int ax, int id) {
		int y = ay, x = ax;
		while(isVisited[y][x] == 0) {
			isVisited[y][x] = id;
			switch(mat[y][x]) {
			case 'U': y -= 1; break;
			case 'D': y += 1; break;
			case 'L': x -= 1; break;
			case 'R': x += 1; break;
			}
		}
		return isVisited[y][x];
	}
	
	static int solution() {
		// 간선의 개수가 정점의 개수와 같으므로 반드시 사이클 발생
		// 독립적인 그래프마다 안전구역을 설치하면 모든 정점에서 사이클로 이동할 수 있음 
		int cnt = 0, id = 1; 
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(isVisited[y][x] != 0) continue;
				
				if(id == findCycle(y, x, id)) ++cnt;
				++id;
			}
		}
		
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int y = 0; y < N; ++y) mat[y] = br.readLine().toCharArray();
		System.out.println(solution());
	}
}