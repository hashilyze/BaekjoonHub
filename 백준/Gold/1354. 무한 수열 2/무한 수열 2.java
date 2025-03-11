import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	// variables
	static long N, P, Q, X, Y;
	static Map<Long, Long> dp = new HashMap<>();
	
	
	static long A(long i) {
		if(i <= 0) return 1L;

		Long val = dp.get(i);
		if(val == null) {
			val = A(i / P - X) + A(i / Q - Y);
			dp.put(i, val);
		}
		return val;
	}
	
	public static void main(String[] args) throws IOException {
		N = readLong();
		P = readLong();
		Q = readLong();
		X = readLong();
		Y = readLong();
		
		System.out.println(A(N));
	}
	
	static long readLong() throws IOException {
		long c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
