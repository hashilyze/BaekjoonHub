import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N, M;
	static long[] fenwick = new long[100_001];
	
	
	static long queryFenwick(int pos) {
		long sum = 0;
		while(pos > 0) {
			sum += fenwick[pos];
			pos -= pos & -pos;
		}
		return sum;
	}
	static void updateFenwick(int pos, int diff) {
		while(pos <= N) {
			fenwick[pos] += diff;
			pos += pos & -pos;
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 1; i <= N; ++i) {
			int v = readInt();
			updateFenwick(i, v);
			updateFenwick(i+1, -v);
		}
		
		M = readInt();
		while(M-- > 0) {
			if(readInt() == 1) {
				int s = readInt(), e = readInt(), v = readInt();
				updateFenwick(s, v);
				updateFenwick(e+1, -v);
			} else {
				sb.append(queryFenwick(readInt())).append("\n");
			}
		}
		System.out.println(sb);
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