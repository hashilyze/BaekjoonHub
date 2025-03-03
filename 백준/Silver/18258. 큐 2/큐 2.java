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
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "push":{
				int x = Integer.parseInt(st.nextToken());
				q.offerLast(x);
			} break;
			case "pop": {
				sb.append(q.isEmpty() ? -1 : q.pollFirst()).append("\n");
				
			} break;
			case "size": {
				sb.append(q.size()).append("\n");
			} break;
			case "empty": {
				sb.append(q.isEmpty() ? "1" : "0").append("\n");
			} break;
			case "front": {
				sb.append(q.isEmpty() ? -1 : q.peekFirst()).append("\n");
			} break;
			case "back": {
				sb.append(q.isEmpty() ? -1 : q.peekLast()).append("\n");
			} break;
			}
		}
		// Output
		System.out.print(sb);
	}
}