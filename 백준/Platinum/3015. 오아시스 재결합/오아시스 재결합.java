import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		long cnt = 0;
		Deque<int[]> stk = new ArrayDeque<int[]>();
		for(int i = 0; i < N; ++i) {
			int height = Integer.parseInt(br.readLine());
			// height를 push했을 때 단조증가수열을 유지하도록 변경
			while(!stk.isEmpty() && stk.peekLast()[0] < height) {
				cnt += stk.peekLast()[1];
				stk.pollLast();
			}
			if(!stk.isEmpty()) {
				if(stk.peekLast()[0] == height) {
					cnt += stk.peekLast()[1];
					if(stk.size() > 1) ++cnt;
					++stk.peekLast()[1];
					continue;
				}
				++cnt;
			}
			stk.offerLast(new int[]{height, 1});
		}
		bw.append(cnt+"").flush();;
	}
}