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
	static long[][] fenwick = new long[SIZE + 1][SIZE + 1];
	
	
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
		N = readInt(); M = readInt(); Q = readInt();
		while(Q-- > 0) {
			if(readInt() == 1) {
				int x1 = readInt(), y1 = readInt(), x2 = readInt(), y2 = readInt(), d = readInt();
				updateFenwick(y1, x1, d);
				updateFenwick(y1, x2+1, -d);
				updateFenwick(y2+1, x1, -d);
				updateFenwick(y2+1, x2+1, d);
			} else {
				int x = readInt(), y = readInt();
				sb.append(queryFenwick(y, x)).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	static int readInt() throws IOException{
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}