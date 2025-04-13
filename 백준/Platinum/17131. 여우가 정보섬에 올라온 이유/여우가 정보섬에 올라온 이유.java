import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Pos{
		int ox, oy, cx, cy, cnt;
		public Pos(int ox, int oy) { this.ox = ox; this.oy = oy; }
		
		@Override
		public String toString() {
			return "Pos [ox=" + ox + ", oy=" + oy + ", cx=" + cx + ", cy=" + cy + ", cnt=" + cnt + "]";
		}
	}
	// constants
	static final int SIZE = 200_000;
	static final int MOD = 1_000_000_007;
	// variables
	static int N;
	static Pos[] positions = new Pos[SIZE + 1];
	static int[] fenwick = new int[SIZE + 1];
	
	
	static int queryFenwick(int pos) {
		int sum = 0;
		while(pos > 0) {
			sum += fenwick[pos];
			pos -= pos & -pos;
		}
		return sum;
	}
	static void updateFenwick(int pos, int diff) {
		while(pos <= SIZE) {
			fenwick[pos] += diff;
			pos += pos & -pos;
		}
	}
	
	static void compressY() {
		int cy = 0;
		Arrays.sort(positions, 0, N, (lhs, rhs)->lhs.oy-rhs.oy);
		
		int prev = Integer.MIN_VALUE;
		for(int i = 0; i < N; ++i) {				
			if(prev != positions[i].oy) ++cy;
			prev = positions[i].oy;
			positions[i].cy = cy;
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) positions[i] = new Pos(readInt(), -readInt());
		compressY();

		// x축 오름차; y축 오름차
		Arrays.sort(positions, 0, N, (lhs, rhs)-> lhs.ox != rhs.ox ? lhs.ox-rhs.ox : rhs.oy-lhs.oy);
		for(int i = 0; i < N; ++i) {
			positions[i].cnt = queryFenwick(positions[i].cy-1);
			updateFenwick(positions[i].cy, 1);
		}
		
		// x축 내림차; y축 오름차
		Arrays.fill(fenwick, 1, N + 1, 0);
		Arrays.sort(positions, 0, N, (lhs, rhs)-> lhs.ox != rhs.ox ? rhs.ox-lhs.ox : rhs.oy-lhs.oy);
		long sum = 0;
		for(int i = 0; i < N; ++i) {
			sum = (sum +  (long)queryFenwick(positions[i].cy-1) * positions[i].cnt) % MOD;
			updateFenwick(positions[i].cy, 1);
		}
		System.out.print(sum);
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