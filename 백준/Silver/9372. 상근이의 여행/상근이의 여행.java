import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			int N = readInt(), M = readInt();
			while(M-- > 0) {
				readInt();
				readInt();
			}
			
			sb.append(N - 1).append("\n");
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