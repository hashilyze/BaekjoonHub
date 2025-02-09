import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		String formula  = br.readLine();
		int ans = solution(formula);
		bw.append(""+ans).flush();
	}
	
	static int solution(String formula) {
		Deque<Integer> stk = new ArrayDeque<Integer>();
		for(char ch : formula.toCharArray()) {
			if(ch == '(') {
				stk.offerLast(-1);
			} else if(ch == ')') {
				int sum = 0;
				while(stk.peekLast() > 0) {
					sum += stk.pollLast();
				}
				stk.pollLast();
				stk.offerLast(sum);
			} else if(Character.isDigit(ch)){
				int val = stk.pollLast();
				stk.offerLast((ch - '0') * val);
			} else {
				switch(ch) {
				case 'H': stk.offerLast(1); break;
				case 'C': stk.offerLast(12); break;
				case 'O': stk.offerLast(16); break;
				}
			}
		}
		int sum = 0;
		while(!stk.isEmpty()) sum += stk.pollLast();
		return sum;
	}
}