import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[] buildings = new int[N];
		for(int i = 0; i < N; ++i) {
			buildings[i] = Integer.parseInt(br.readLine());
		}
		
		long sum = 0;
		Deque<Integer> stk = new ArrayDeque<Integer>();
		stk.offerLast(1_000_000_000 + 1);
		for(int i = 0; i < N; ++i) {
			int height = buildings[i];
			while(stk.peekLast() <= height) stk.pollLast();
			stk.offerLast(height);
			sum += stk.size() - 2;
		}
		bw.append(sum+"").flush();
	}
}