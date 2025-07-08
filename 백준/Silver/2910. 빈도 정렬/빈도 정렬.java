import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N, C;
	static Integer[] A;
	static Map<Integer, Integer> freqs = new HashMap<>();
	static Map<Integer, Integer> firsts = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		N = readInt(); C = readInt();
		A = new Integer[N];
		for(int i = 0; i < N; ++i) {
			A[i] = readInt();
			freqs.put(A[i], freqs.getOrDefault(A[i], 0) + 1);
			firsts.putIfAbsent(A[i], i);
		}
		
		Arrays.sort(A, (lhs, rhs) -> {
			int freqLhs = freqs.get(lhs);
			int freqRhs = freqs.get(rhs);
			if(freqLhs != freqRhs) return freqRhs - freqLhs;
			return firsts.get(lhs) - firsts.get(rhs);
		});
		
		for(int i = 0; i < N; ++i) sb.append(A[i]).append(" "); 
		System.out.print(sb);
		
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}