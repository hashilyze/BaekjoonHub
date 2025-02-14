import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static String stmt;
	
	static int result = Integer.MIN_VALUE;
	
	
	static int evaluate(List<Character> postStmt) {
		Deque<Integer> vStk = new ArrayDeque<Integer>(postStmt.size());
		for(char token : postStmt) {
			if(Character.isDigit(token)) {
				vStk.offerLast(token - '0');
			} else {
				int rhs = vStk.pollLast();
				char op = token; 
				int lhs = vStk.pollLast();
				
				int ret = -1;
				switch(op) {
				case '+': ret = lhs + rhs; break;
				case '-': ret = lhs - rhs; break;
				case '*': ret = lhs * rhs; break;
				}
				vStk.offerLast(ret);
			}
		}
		return vStk.pollLast();
	}
	
	// 후위표현식 생성
	static void nextMonotone(int idx, int N, Deque<Character> stk, List<Character> postStmt) {
		if(idx >= N) {
			if(postStmt.size() == N) {
				result = Math.max(result, evaluate(postStmt));
			}
			return;
		}
		
		// 백트래킹
		char ch = stmt.charAt(idx);
		if(Character.isDigit(ch)) {
			postStmt.add(ch);
			// 연산자가 현재 위치에 배치될치 결정 
			nextMonotone(idx + 1, N, stk, postStmt);
			while(!stk.isEmpty()) {
				postStmt.add(stk.pollLast());
				nextMonotone(idx + 1, N, stk, postStmt);
			}
			while(!Character.isDigit(postStmt.get(postStmt.size() - 1))) {
				stk.offerLast(postStmt.get(postStmt.size() - 1));
				postStmt.remove(postStmt.size() - 1);
			}
			postStmt.remove(postStmt.size() - 1);
		} else {
			stk.offerLast(ch);
			nextMonotone(idx + 1, N, stk, postStmt);
			stk.pollLast();
		}
	}
	
	static int solution() {
		Deque<Character> opStk = new ArrayDeque<Character>(N);
		List<Character> postStmt = new ArrayList<Character>(N);
		nextMonotone(0, N, opStk, postStmt);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stmt = br.readLine();
		bw.append(""+solution()).flush();;
	}
}
	