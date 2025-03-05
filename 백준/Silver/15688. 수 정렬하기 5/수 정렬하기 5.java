import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] A = new int[1_000_000];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		Arrays.sort(A, 0, N);
		for(int i = 0; i < N; ++i) sb.append(A[i]).append("\n");
		System.out.print(sb);
	}
	
	static int readInt() throws IOException { 
		int c, n, s = 1;
		while((c = System.in.read()) < 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n * s;
	}
}