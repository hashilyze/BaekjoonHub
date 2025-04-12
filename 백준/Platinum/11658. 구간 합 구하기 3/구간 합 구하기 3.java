import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1024; 
	// variables
	static int N, M;
	static int[][] A = new int[SIZE + 1][SIZE + 1];
	static int[][] fenwick = new int[SIZE + 1][SIZE + 1];
	
	
	static int queryFenwick(int rangeY, int rangeX) {
		int sum = 0;
		int memo = rangeX;
		while(rangeY > 0) {
			rangeX = memo;
			while(rangeX > 0) {
				sum += fenwick[rangeY][rangeX];
				rangeX -= rangeX & -rangeX;
			}
			rangeY -= rangeY & -rangeY;
		}
		return sum;
	}
	static void updateFenwick(int posY, int posX, int diff) {
		int memo = posX;
		while(posY <= N) {
			posX = memo;
			while(posX <= N) {
				fenwick[posY][posX] += diff;
				posX += posX & -posX;
			}
			posY += posY & -posY;
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		for(int r = 1; r <= N; ++r) {
			for(int c = 1; c <= N; ++c) {
				updateFenwick(r, c, A[r][c] = readInt());
			}
		}
		
		for(int q = 0; q < M; ++q) {
			if(readInt() == 0) {
				int r = readInt(), c = readInt(), v = readInt();
				updateFenwick(r, c, v - A[r][c]);
				A[r][c] = v;
			} else { // == 1
				int r1 = readInt(), c1 = readInt(), r2 = readInt(), c2 = readInt();
				int ans = queryFenwick(r2, c2)
						- queryFenwick(r2, c1 - 1)
						- queryFenwick(r1 - 1, c2)
						+ queryFenwick(r1 - 1, c1 - 1);
				sb.append(ans).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}