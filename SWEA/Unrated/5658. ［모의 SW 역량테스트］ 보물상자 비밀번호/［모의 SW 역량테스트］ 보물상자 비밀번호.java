import java.io.*;
import java.util.*;

public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N, K;
	static char[] word = new char[28 << 1];

	static int solution() {
		int quater = N >> 2;
		TreeSet<Integer> s = new TreeSet<>();
		for(int i = 0; i < quater; ++i) {
			for(int j = 0; j < 4; ++j) {
				s.add(Integer.parseInt(new String(word, i + j*quater, quater), 16));
			}
		}
		
		for(int v : s.descendingSet()) {
			if(--K == 0) return v;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			N = readInt(); K = readInt();
			for(int i = 0; i < N; ++i) word[i] = word[N + i] = (char)System.in.read();
			if(System.in.read() == '\r') System.in.read();
			
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}