import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		List<Character> ans = solution(N);
		if(ans == null) {
			bw.append("NO").flush();
		} else {
			for(char e : ans) bw.append(e).append('\n');
		}
		bw.flush();
	}
	
	static List<Character> solution(int N) throws NumberFormatException, IOException {
		List<Character> li = new ArrayList<Character>();
		
		Deque<Integer> stk = new ArrayDeque<Integer>();
		int q = 1;
		for(int i = 0; i < N; ++i) {
			int target = Integer.parseInt(br.readLine());
			if(!stk.isEmpty() && target < stk.peekLast()) {
				return null;
			}
			
			while(q <= target &&  q <= N) {
				stk.offerLast(q++);
				li.add('+');
			}
			
			if(!stk.isEmpty() && stk.peekLast() == target) {
				stk.pollLast();
				li.add('-');
			}
		}
		
		return li;
	}
}