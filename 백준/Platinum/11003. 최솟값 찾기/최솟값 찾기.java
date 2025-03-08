import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	// variables
	
	
	
	public static void main(String[] args) throws IOException {
		int N = readInt(), L = readInt();
		Deque<int[]> deq = new ArrayDeque<int[]>();
		for(int i = 0; i < N; ++i) {
			int val = readInt();
			while(!deq.isEmpty() && deq.peekLast()[0] >= val) deq.pollLast();
			deq.offerLast(new int[] {val, i});
			
			while(deq.peek()[1] <= i - L) deq.pollFirst();
			sb.append(deq.peek()[0]).append(" ");
		}
		System.out.print(sb);
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