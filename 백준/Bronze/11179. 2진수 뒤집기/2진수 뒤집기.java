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
	
	public static void main(String[] args) throws IOException {
		int n = readInt();
		int m = 0;
		while(n > 0) {
			m = (m << 1) | (n & 1);
			n >>= 1;
		}
		System.out.print(m);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		return n;
	}
}