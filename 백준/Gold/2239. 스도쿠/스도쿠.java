import java.io.*;
import java.util.*;

public class Main {
	// io
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 9;
	// variables
	static char[][] mat = new char[SIZE][SIZE];
	
	static int[] rows = new int[SIZE];
	static int[] cols = new int[SIZE];
	static int[] grid = new int[SIZE];
	
	static int emptyCnt = 0;
	static int[][] empties = new int[SIZE * SIZE][2];
	
	
	static int getGridIndex(int y, int x) { return (y / 3 * 3) + (x / 3); }
	
	static boolean dfs(int idx) {
		if(idx == emptyCnt) { // 기저
			return true;
		}
		
		int x = empties[idx][0];
		int y = empties[idx][1];
		for(int i = 0; i < SIZE; ++i) {
			final int mask = 0x01 << i;
			int g = getGridIndex(y, x);
			// 빈칸에 사용할 수 있는 값인지 확인
			if(((rows[y] & mask) | (cols[x] & mask) | (grid[g] & mask)) != 0) continue;
			
			rows[y] ^= mask;
			cols[x] ^= mask;
			grid[g] ^= mask;
			
			mat[y][x] = (char)(i + ('0' + 1));
			if(dfs(idx + 1)) return true;
			
			rows[y] ^= mask;
			cols[x] ^= mask;
			grid[g] ^= mask;
		}
		return false;
	}
	
	static void solution() {
		dfs(0);
	}
	
	public static void main(String[] args) throws IOException {
		for(int y = 0; y < SIZE; ++y) {
			for(int x = 0; x < SIZE; ++x) {
				char c = readDigit();
				if(((mat[y][x] = c) & 0x0F) == 0) {
					empties[emptyCnt][0] = x;
					empties[emptyCnt][1] = y;
					++emptyCnt;
					continue;
				}
				// 행, 열, 격자에 존재하는 숫자 기록
				int shift = (c & 0x0F) - 1;
				rows[y] |= 0x01 << shift;
				cols[x] |= 0x01 << shift;
				grid[getGridIndex(y, x)] |= 0x01 << shift;
			}
		}
		
		solution();
		
		for(int y = 0; y < SIZE; ++y) {
			sb.append(mat[y]).append("\n");
		}
		System.out.print(sb);
	}
	
	// 숫자 문자 한 글자 반환
	static char readDigit() throws IOException {
		int c;
		while((c = System.in.read()) < 0x30) ;
		return (char)c;
	}
}