import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	
	static int[][] DELTA = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static char[][] mat;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		if(N == 1) bw.append('*').flush();
		else {
			int width = 4 * (N - 1) + 1;
			int height = 4 * N - 1;
			mat = new char[height][width];
			for(int i = 2; i < height; ++i) Arrays.fill(mat[i], ' ');
			
			
			int x = width, y = 0;
			while(0 < x) mat[y][--x] = '*';
			
			int dir = 1;
			while(true) {
				for(int i = 0; i < height; ++i) {
					mat[y][x] = '*';
					x += DELTA[dir][0];
					y += DELTA[dir][1];
				}
				height -= 2;
				x -= DELTA[dir][0];
				y -= DELTA[dir][1];
				dir = (dir + 1) % DELTA.length;
				
				if(width == 1 && height == 1) break;
				
				for(int i = 0; i < width; ++i) {
					mat[y][x] = '*';
					x += DELTA[dir][0];
					y += DELTA[dir][1];
				}
				width -= 2;
				x -= DELTA[dir][0];
				y -= DELTA[dir][1];
				dir = (dir + 1) % DELTA.length;
			}
			
			System.out.println(mat[0]);;
			System.out.println('*');
			for(int i = 2; i < mat.length; ++i) System.out.println(mat[i]);
		}
		
	}
}