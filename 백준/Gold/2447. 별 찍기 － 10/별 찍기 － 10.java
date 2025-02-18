import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static char[][] mat;
	static int[][] DELTA = {
			{0, 0}, {0, 1}, {0, 2},
			{1, 0},         {1, 2},
			{2, 0}, {2, 1}, {2, 2},
	};
	
	static void makeStar(int ay, int ax, int len) {
		if(len == 1) {
			mat[ay][ax] = '*';
			return;
		}
		
		int nextLen = len / 3;
		for(int i = 0; i < DELTA.length; ++i) {
			makeStar(ay + nextLen * DELTA[i][1], ax + nextLen * DELTA[i][0], nextLen);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		mat = new char[N][N];
		for(int i = 0; i < N; ++i) {
			Arrays.fill(mat[i], ' ');
		}
		makeStar(0, 0, N);
		for(int i = 0; i < N; ++i) {
			System.out.println(mat[i]);
		}
	}
}