import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		long N = Integer.parseInt(br.readLine());
		
		long cnt = 0;
		Deque<long[]> stk = new ArrayDeque<long[]>();
		stk.offerLast(new long[] {2147483648L, 1});
		for(int i = 0; i < N; ++i) {
			long height = Integer.parseInt(br.readLine());
			// height를 push했을 때 단조증가수열을 유지하도록 변경
			while(stk.peekLast()[0] < height) {
				cnt += stk.peekLast()[1];
				stk.pollLast();
			}
			if(stk.size() > 1) { 
				if(stk.peekLast()[0] == height) {
					cnt += stk.peekLast()[1];
					if(stk.size() > 2) ++cnt;
				} else {
					++cnt;
				}
			}
			if(stk.peekLast()[0] == height) {
				++stk.peekLast()[1];
			} else {
				stk.offerLast(new long[]{height, 1});
			}
			
		}
		bw.append(cnt+"").flush();
	}
}