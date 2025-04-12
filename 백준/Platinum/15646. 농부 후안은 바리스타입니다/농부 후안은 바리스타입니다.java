import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 5_000; 
	// variables
	static int N, M, Q;
	static long[][] fenwick = new long[SIZE + 2][SIZE + 2];
	
	
	static long queryFenwick(int y, int x) {
		long sum = 0;
		int memo = x;
		while(y > 0) {
			x = memo;
			while(x > 0) {
				sum += fenwick[y][x];
				x -= x & -x;
			}
			y -= y & -y;
		}
		return sum;
	}
	static void updateFenwick(int y, int x, int d) {
		int memo = x;
		while(y <= M) {
			x = memo;
			while(x <= N) {
				fenwick[y][x] += d;
				x += x & -x;
			}
			y += y & -y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken()); 
			if(cmd == 1) {
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				updateFenwick(y1, x1, d);
				updateFenwick(y1, x2+1, -d);
				updateFenwick(y2+1, x1, -d);
				updateFenwick(y2+1, x2+1, d);
			} else {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				sb.append(queryFenwick(y, x)).append("\n");
			}
		}
		System.out.print(sb);
	}
}