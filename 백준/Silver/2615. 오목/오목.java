import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static final int SPACE = 0, BLACK = 1, WHITE = 2;
	static final int SIZE = 19, LENGTH = 5;
	static final int[][] board = new int[SIZE][SIZE];
	
	public static void main(String[] args) throws IOException{
		// Input
		for(int y = 0; y < SIZE; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < SIZE; ++x) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Solution
		int ans = SPACE, sy = -1, sx = -1;
		for(int y = 0; y < SIZE && ans == SPACE; ++y) {
			for(int x = 0; x < SIZE && ans == SPACE; ++x) {
				ans = howAreWin(y, x);
				if(ans != SPACE) {
					sy = y;
					sx = x;
				}
			}
		}
		
		// Output
		bw.append(ans+"\n");
		if(ans != SPACE) {
			bw.append((sy + 1) + " " + (sx + 1));
		}
		bw.flush();
	}
	
	static boolean isSafePosition(int y, int x) {
		return 0 <= y && y < SIZE && 0 <= x && x < SIZE; 
	}
	
	static int maxLength(int y, int x, int mark, int dy, int dx) {
		int length = 0;
		while(isSafePosition(y, x) && board[y][x] == mark) {
			++length;
			y += dy;
			x += dx;
		}
		return length;
	}
	
	static int howAreWin(int y, int x) {
		final int mark = board[y][x];
		if(mark == SPACE) return 0;
		
		if(maxLength(y, x, mark, 1, 0) == LENGTH && maxLength(y, x, mark, -1, 0) == 1) return mark;
		if(maxLength(y, x, mark, 0, 1) == LENGTH && maxLength(y, x, mark, 0, -1) == 1) return mark;
		if(maxLength(y, x, mark, 1, 1) == LENGTH && maxLength(y, x, mark, -1, -1) == 1) return mark;
		if(maxLength(y, x, mark, -1, 1) == LENGTH && maxLength(y, x, mark, 1, -1) == 1) return mark;
		return 0;
	}
}
