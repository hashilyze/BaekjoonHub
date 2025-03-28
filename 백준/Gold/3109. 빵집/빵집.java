import java.io.*;
import java.util.*;


public class Main {
	// IOHandler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st = null;
	// constants
	static final int MAX_R = 10_000;
	static final int MAX_C = 500;
	static final int[] dy = { -1, 0, 1};
	// variables
	static int R, C;
	static boolean[][] isVisited = new boolean[MAX_R][MAX_C];
	
	
	static boolean inRange(int y) { return 0 <= y && y < R;  }
	
	static boolean countPipe(int y, int x) {
		if(x == C - 1) {
			return true;
		}
		
		for(int i = 0; i < dy.length; ++i) {
			int nx = x + 1;
			int ny = y + dy[i];
			if(inRange(ny) && !isVisited[ny][nx]) {
				isVisited[ny][nx] = true;
				if(countPipe(ny, nx)) return true;
			}
		}
		return false;
	}
	
	static int solution() {	
		int cnt = 0;
		for(int y = 0; y < R; ++y) {
			if(countPipe(y, 0)) ++cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < R; ++i) {
			String line = br.readLine();
			for(int j = 0; j < C; ++j) {
				isVisited[i][j] = line.charAt(j) == 'x';
			}
		}
		System.out.println(solution());
	}
}