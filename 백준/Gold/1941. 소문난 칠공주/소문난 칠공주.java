import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final char S = 'S';
	static final char Y = 'Y';
	static final int SIZE = 5;
	static final int PICK = 7;
	static final int[][] DELTA = new int[][] { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	
	
	static final int[][] arr = new int[SIZE * SIZE][2];
	static char[][] mat = new char[SIZE][SIZE];
	static List<Integer> comb = new ArrayList<Integer>();
	static int cnt = 0;
	
	
	static boolean isLinked() {
		boolean[][] isVisited = new boolean[SIZE][SIZE];
		for(int e : comb) {
			int x = arr[e][0];
			int y = arr[e][1];
			isVisited[y][x] = true;
		}
		int cnt = 1;
		Deque<int[]> q = new ArrayDeque<int[]>();
		isVisited[arr[comb.get(0)][1]][arr[comb.get(0)][0]] = false;
		q.add(arr[comb.get(0)]);
		
		while(!q.isEmpty()) {
			int[] u = q.pollFirst();
			
			for(int i = 0; i < DELTA.length; ++i) {
				int ny = u[1] + DELTA[i][1], nx = u[0] + DELTA[i][0]; 
				if(0 <= nx && nx < SIZE && 0 <= ny && ny < SIZE && isVisited[ny][nx]) {
					isVisited[ny][nx] = false;
					q.offerLast(new int[] {nx, ny});
					++cnt;
				}
			}
		}
		return cnt == PICK;
	}
	static boolean isOverwhelm() {
		int cntS = 0, cntY = 0;
		for(int e : comb) {
			int x = arr[e][0];
			int y = arr[e][1];
			
			if(mat[y][x] == S) ++cntS;
			else if(mat[y][x] == Y) ++cntY;
		}
		return cntS > cntY;
	}
	
	static void nextCombination(int idx, int left) {
		if(left == 0) {
			if(isOverwhelm() && isLinked()) {
				++cnt;
			}
			return;
		}
		
		for(int i = idx; i < SIZE * SIZE - left + 1; ++i) {
			comb.add(i);
			nextCombination(i + 1, left - 1);
			comb.remove(comb.size() - 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < SIZE * SIZE; ++i) arr[i] = new int[] { i / SIZE, i % SIZE};
		
		for(int y = 0; y < SIZE; ++y) {
			String line = br.readLine();
			for(int x = 0; x < SIZE; ++x) {
				mat[y][x] = line.charAt(x);
			}
		}
		nextCombination(0, PICK);
		System.out.println(cnt);
	}
}
