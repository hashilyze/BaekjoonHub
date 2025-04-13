import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Pos{
		int ox, oy, cx, cy;
		public Pos(int ox, int oy) { this.ox = ox; this.oy = oy; }
		
		@Override
		public String toString() {
			return "Pos [ox=" + ox + ", oy=" + oy + ", cx=" + cx + ", cy=" + cy + "]";
		}
	}
	// constants
	static final int SIZE = 75_000;
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
	
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			N = readInt();
			for(int i = 0; i < N; ++i) positions[i] = new Pos(readInt(), -readInt());
			Arrays.fill(fenwick, 1, N+1, 0);

			// Y축 압축
			{
				int cy = 0;
				Arrays.sort(positions, 0, N, (lhs, rhs)->lhs.oy-rhs.oy);
				
				int prev = Integer.MIN_VALUE;
				for(int i = 0; i < N; ++i) {				
					if(prev != positions[i].oy) ++cy;
					prev = positions[i].oy;
					positions[i].cy = cy;
				}
			}
			Arrays.sort(positions, 0, N, (lhs, rhs)-> lhs.ox != rhs.ox ? lhs.ox-rhs.ox : lhs.oy-rhs.oy);
			
			long sum = 0;
			for(int i = 0; i < N; ++i) {
				sum += queryFenwick(positions[i].cy);
				updateFenwick(positions[i].cy, 1);
			}
			sb.append(sum).append("\n");
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