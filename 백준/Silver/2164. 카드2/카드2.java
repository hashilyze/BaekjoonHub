import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static Deque<Integer> q = new ArrayDeque<Integer>();
	
	
	public static void main(String[] args) throws IOException {
		// Input
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; ++i) q.offerLast(i);
		
		while(q.size() > 1) {
			q.pollFirst();
			q.offerLast(q.pollFirst());
		}
		// Output
		System.out.print(q.pollFirst());
	}
}