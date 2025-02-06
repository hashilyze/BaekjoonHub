import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] ans = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());

		
		Deque<Integer> stk = new ArrayDeque<Integer>();
		stk.offerLast(1_000_001);
		for(int i = N - 1; i >= 0; --i) {
			int val = arr[i];
			while(stk.peekLast() <= val) stk.pollLast();
			
			ans[i] = stk.peekLast();
			if(ans[i] == 1_000_001) ans[i] = -1;
			
			stk.offer(val);
		}
		
		for(int e : ans) bw.append(e + " ");
		bw.flush();
	}
}