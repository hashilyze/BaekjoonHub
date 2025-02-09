import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		String txt = br.readLine();
		int ans = solution(txt);
		bw.append(""+ans).flush();
	}
	
	static int solution(String txt) {
		int ans = 0;
		int tmp = 1;
		Deque<Character> stk = new ArrayDeque<Character>();
		for(int i = 0; i < txt.length(); ++i) {
			char ch = txt.charAt(i);
			if(ch == '(' || ch == '[') {
				tmp *= ch == '(' ? 2 : 3;
				stk.offerLast(ch);
			} else if(ch == ')' || ch == ']') {
				if(stk.isEmpty() || Math.abs(stk.pollLast() - ch) > 2) return 0;
				if(txt.charAt(i - 1) == '(' || txt.charAt(i - 1) == '[') ans += tmp;
				tmp /= ch == ')' ? 2 : 3;
			}
		}
		return stk.isEmpty() ? ans : 0;
	}
}