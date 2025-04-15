import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N, K, Q;
	static long[] A = new long[1_000_000];
	static Map<Integer, Integer> set = new HashMap<>();
	
	
	static void something(int jump, int d) {
	    int i = 0;
	    while (i < N) {
	        A[i] = A[i] + d;
	        i = i + jump;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); K = readInt();
		for(int i = 0; i < K; ++i) {
			int x = readInt();
			set.put(x, set.getOrDefault(x, 0) + 1);
		}
		
		for(Entry<Integer, Integer> entry : set.entrySet()) {
			something(entry.getKey(), entry.getValue());
		}
		for(int i = 1; i < N; ++i) A[i] += A[i - 1];
		
		Q = readInt();
		while(Q-- > 0) {
			int L = readInt(), R = readInt();
			sb.append(A[R] - (L == 0 ? 0 : A[L - 1])).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}