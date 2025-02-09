import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static final char OPEN2 = '(';
	static final char CLOSE2 = ')';
	static final char OPEN3 = '[';
	static final char CLOSE3 = ']';
	
	public static void main(String[] args) throws IOException {
		String txt = br.readLine();
		int ans = solution(txt);
		bw.append(""+ans).flush();
	}
	
	static boolean isValid(String txt) {
		Deque<Character> stk = new ArrayDeque<Character>();
		for(char ch : txt.toCharArray()) {
			if(ch == OPEN2 || ch == OPEN3) {
				stk.offerLast(ch);
			} else {
				if(stk.isEmpty()) return false;
				
				if(stk.peekLast() == OPEN2) {
					if(ch != CLOSE2) return false;
				} else { // stk.peekLast() == OPEN3
					if(ch != CLOSE3) return false;
				}
				stk.pollLast();
			}
		}
		if(!stk.isEmpty()) return false;
		return true;
	}
	
	static int solution(String txt) {
		if(!isValid(txt)) return 0;
		
		Deque<Integer> stk = new ArrayDeque<Integer>();
		stk.offerLast(0);
		
		for(char ch : txt.toCharArray()) {
			if(ch == OPEN2 || ch == OPEN3) {
				stk.offerLast(-ch);
			} else {
				int val = ch == CLOSE2 ? 2 : 3;
				if(stk.peekLast() > 0) { // (x)
					val *= stk.pollLast();
				}
				
				stk.pollLast();
				if(stk.peekLast() > 0) { // x()
					val += stk.pollLast();
				}
				
				stk.offerLast(val);
			}
		}
		return stk.peekLast();
	}
}