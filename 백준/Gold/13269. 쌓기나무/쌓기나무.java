import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N, M;
	static int[][] mat;
	static int[] front;
	static int[] right;
	
	
	static boolean solution() {
		// 앞에서 보았을 때 최대 블록 수를 지정
		for(int x = 0; x < M; ++x) {
			int upper = front[x];
			for(int y = 0; y < N; ++y) {
				if(mat[y][x] > 0) mat[y][x] = upper;
			}
		}
		// 오른쪽에서 보았을 때 최대 블록 수를 넘지 않도록 제거
		for(int y = 0; y < N; ++y) { 
			int lower = right[y];
			for(int x = 0; x < M; ++x) {
				if(mat[y][x] > 0) mat[y][x] = Math.min(mat[y][x], lower);
			}
		}
		
		// 앞에서 보았을 때 모습이 입력과 같은 지 확인
		for(int x = 0; x < M; ++x) { 
			int max = 0;
			for(int y = 0; y < N; ++y) {
				max = Math.max(max, mat[y][x]);
			}
			if(front[x] != max) return false;
		}
		// 오른쪽에서 보았을 때 모습이 입력과 같은 지 확인
		for(int y = 0; y < N; ++y) { 
			int max = 0;
			for(int x = 0; x < M; ++x) { 			
				max = Math.max(max, mat[y][x]);
			}
			if(right[y] != max) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt(); M = readInt();
		mat = new int[N][M];
		front = new int[M];
		right = new int[N];
		for(int y = 0; y < N; ++y) { // 위에서 보았을 때
			for(int x = 0; x < M; ++x) {
				mat[y][x] = readInt();
			}
		}
		for(int x = 0; x < M; ++x) front[x] = readInt(); // 위에서 보았을 때
		for(int y = N - 1; y >= 0; --y) right[y] = readInt(); // 오른쪽에서 보았을 때
		// Solution
		
		// Output
		if(solution()) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < M; ++j) {
					sb.append(mat[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
		} else {
			System.out.print(-1);
		}
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
