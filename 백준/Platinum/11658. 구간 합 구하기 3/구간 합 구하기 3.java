import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int SIZE = 1024;
	// variables
	static int N, M;
	static int[][] arr = new int[SIZE + 1][SIZE + 1];
	static int[][] fenwick = new int[SIZE + 1][SIZE + 1];
	
	
	static void updateFenwick(int x, int y, int val) {
		for(int i = x; i <= N; i += (i & -i)) {
			for(int j = y; j <= N; j += (j & -j)) {
				fenwick[i][j] += val;
			}
		}
	}
	static int queryFenwick(int x, int y) {
		int ans = 0;
		for(int i = x; i > 0; i -= (i & -i)) {
			for(int j = y; j > 0; j -= (j & -j)) {
				ans += fenwick[i][j];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		for(int x = 1; x <= N; ++x) {
			for(int y = 1; y <= N; ++y) {
				arr[x][y] = readInt();
				updateFenwick(x, y, arr[x][y]);
			}
		}
		
		while(M-- > 0) {
			int w = readInt();
			switch(w) {
			case 0:{
				int x = readInt(), y = readInt(), c = readInt();
				updateFenwick(x, y, c - arr[x][y]);
				arr[x][y] = c;
			} break;
			case 1:{
				int x1 = readInt(), y1 = readInt();
				int x2 = readInt(), y2 = readInt();
				
				int ans = queryFenwick(x2, y2)
						- queryFenwick(x1 - 1, y2)
						- queryFenwick(x2, y1 - 1)
						+ queryFenwick(x1 - 1, y1 - 1);
				
				sb.append(ans).append("\n");
			} break;
			}
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		return n;
	}
}