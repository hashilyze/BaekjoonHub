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
	static List<String> li = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		int N;
		while((N = readInt()) != -1) {
			int sum = 1;
			
			li.clear(); li.add("1");
			
			int p = 2;
			while(p * p < N) {
				if(N % p == 0) {
					sum += p + (N / p);
					li.add(Integer.toString(p)); li.add(Integer.toString(N / p));
				}
				++p;
			}
			if(p * p == N) {
				sum += p;
				li.add(Integer.toString(p));
			}
			
			if(sum == N) {
				Collections.sort(li, (lhs, rhs)->Integer.parseInt(lhs)-Integer.parseInt(rhs));
				System.out.println(N + " = " + String.join(" + ", li));
			} else {
				System.out.println(N + " is NOT perfect.");
			}
		}
	}
	
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		return n * s;
	}
}