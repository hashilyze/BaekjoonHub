import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, a, b;
	
	static int solution() {
		Set<Integer> hits = new HashSet<>();
		
		int cnt = 0;
		int x = 0;
		while(true) {
			if(hits.contains(x)) break;
			hits.add(x);
			x = (int)((((((long)a * x) % N) * x) % N + b) % N);
			
		}
		int anchor = x;
		while(true) {
			++cnt;
			x = (int)((((((long)a * x) % N) * x) % N + b) % N);
			if(anchor == x) return N - cnt;
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
			sb.append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}