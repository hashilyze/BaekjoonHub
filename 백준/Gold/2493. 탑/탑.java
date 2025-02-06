import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] ans = new int[N];
		Deque<int[]> stk = new ArrayDeque<int[]>();
		stk.offer(new int[] {0, 100_000_001});
		
		for(int i = 0; i < ans.length; ++i) {
			int num = i + 1;
			int height = Integer.parseInt(st.nextToken());
			
			while(stk.peekLast()[1] < height) stk.pollLast();
			ans[i] = stk.peekLast()[0];
			stk.offer(new int[] {num, height});
		}
		
		
		for(int e : ans) bw.append(e + " ");
		bw.flush();
	}
}