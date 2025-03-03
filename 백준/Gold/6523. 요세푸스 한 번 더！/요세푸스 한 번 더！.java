import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, a, b;
	
	static int solution() {
		Map<Integer, Integer> hits = new HashMap<>();
		
		int x = 0;
		int next = 0;
		while(true) {
			if(hits.containsKey(x)) return N - (next - hits.get(x));
			
			hits.put(x, next++);
			x = (int)((((((long)a * x) % N) * x) % N + b) % N);
		}
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			// Input
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			// Output
			System.out.println(solution());
			System.gc();
		}
	}
}