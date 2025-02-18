import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	
	
	static final int[][] DELTA = {
	            		 {2, -2},
	            {1, -1},          {3, -1},
	   {0,  0}, {1,  0}, {2,  0}, {3,  0}, {4,  0},	
	};
	static char[][] mat;
	
	
	static void makeStar(int ay, int ax, int m) {
		if(m == 3) {
			for(int i = 0; i < DELTA.length; ++i) {
				mat[ay + DELTA[i][1]][ax + DELTA[i][0]] = '*';
			}
			return;
		}
		
		int half = m >> 1;
		makeStar(ay, ax, half);
		makeStar(ay, ax + m, half);
		makeStar(ay - half, ax + half, half);
	}
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		mat = new char[N][2 * N - 1];
		for(int i = 0; i < N; ++i) Arrays.fill(mat[i], ' ');
		
		makeStar(N - 1, 0, N);
		
		for(int i = 0; i < N; ++i) {
			System.out.println(mat[i]);
		}
	}
}