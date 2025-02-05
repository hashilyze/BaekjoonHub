import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine());
		Map<String, Integer> ranks = new HashMap<String, Integer>();
		Deque<Integer> stk = new ArrayDeque<Integer>();
		for(int i = 0; i < N; ++i) {
			ranks.put(br.readLine(), i);
		}
		
		stk.offerLast(-1); // dummy
		for(int i = 0 ; i < N; ++i) {
			String car = br.readLine();
			int carRank = ranks.get(car);
			while(stk.peekLast() > carRank) {
				stk.pollLast();
			}
			stk.offerLast(carRank);
		}
		bw.append(""+(N - stk.size() + 1)).flush();
	}
}