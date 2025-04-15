import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int[][] DELTA = {
			{-1, -1}, {0, -1}, {1, -1},
			{-1,  0},          {1,  0},
			{-1,  1}, {0,  1}, {1,  1},
	};
	static char[][] MBTI = {
		{'E', 'I'},
		{'N', 'S'},
		{'F', 'T'},
		{'P', 'J'},
	};
	// variables
	static int N, M;
	static char[][] mat;
	
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }
	
	static boolean isMbti(int y, int x, int d) {
		for(int i = 0; i < MBTI.length; ++i) {
			if(!inRange(y, x)) return false;
			
			char data = mat[y][x];
			if(data != MBTI[i][0] && data != MBTI[i][1]) return false;
			
			x += DELTA[d][0];
			y += DELTA[d][1];
		}
		return true;
	}
	
	static int solution() {
		int cnt = 0;
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				for(int d = 0; d < DELTA.length; ++d) {
					if(isMbti(y, x, d)) {
						++cnt;
					}
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		mat = new char[N][];
		for(int y = 0; y < N; ++y) mat[y] = br.readLine().toCharArray();
		System.out.print(solution());
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}