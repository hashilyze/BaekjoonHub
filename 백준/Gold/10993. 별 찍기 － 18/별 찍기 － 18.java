import java.io.*;
import java.math.BigInteger;
import java.sql.Array;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	

	static char[][] mat;
	
	static int getWidth(int n) {return 2 * (0x01 << n) - 3;}
	static int getHeight(int n) {return (0x01 << n) - 1;}
	
	static void makeStar(int ay, int ax, int n, int dir) {
		if(n == 1) {
			mat[ay][ax] = '*';
			return;
		}
		int width = getWidth(n);
		int height = getHeight(n);
		if(dir < 0) {
			for(int i = 0; i < width; ++i) 
				mat[ay][ax + i] = '*';
			for(int i = 0; i < height; ++i) 
				mat[ay + i][ax + i] = mat[ay + i][ax + (width - 1) - i] = '*';
		} else if(dir > 0) {
			for(int i = 0; i < width; ++i) 
				mat[ay][ax + i] = '*';
			for(int i = 0; i < height; ++i) 
				mat[ay - i][ax + i] = mat[ay - i][ax + (width - 1) - i] = '*';
		}
		makeStar(ay + -dir * height / 2, ax + width / 4 + 1, n - 1, -dir);
	}

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int width = getWidth(N);
		int height = getHeight(N);
		mat = new char[height][width];
		for(int i = 0; i < height; ++i) Arrays.fill(mat[i], ' ');
		
		if(N % 2 == 0) {
			makeStar(0, 0, N, -1);
			for(int i = 0; i < height; ++i) {
				StringBuilder sb = new StringBuilder().append(mat[i]).replace(width - i, width, "");
				System.out.println(sb.toString());
			}
		} else {
			makeStar(height - 1, 0, N, 1);
			for(int i = 0; i < height; ++i) {
				StringBuilder sb = new StringBuilder().append(mat[i]).replace(width / 2 + i + 1, width, "");
				System.out.println(sb.toString());
			}
		}
	}
}