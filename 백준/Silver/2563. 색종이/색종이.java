import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int BOARD_SIZE = 100;
	static final int PAPER_SIZE = 10;
	
	static int N;
	static boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];
	
	
	static void setPaper(int ay, int ax) {
		for(int dy = 0; dy < PAPER_SIZE; ++dy) {
			for(int dx = 0; dx < PAPER_SIZE; ++dx) {
				board[ay + dy][ax + dx] = true;
			}
		}
	}
	
	static int getArea() {
		int area = 0;
		for(int y = 0; y < BOARD_SIZE; ++y) {
			for(int x = 0; x < BOARD_SIZE; ++x) {
				if(board[y][x]) ++area;
			}
		}
		return area;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int ax = Integer.parseInt(st.nextToken());
			int ay = Integer.parseInt(st.nextToken());
			setPaper(ay, ax);
		}
		System.out.println(getArea());
	}
}
