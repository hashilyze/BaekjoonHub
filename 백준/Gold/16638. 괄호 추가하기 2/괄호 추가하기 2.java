import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;	
	// constants
	static int MAX_N = 10;
	// variables
	static int N;
	static String stmt;
	static int result = Integer.MIN_VALUE;
	
	// N1 OP1 N2 OP2 N3 OP3 N4
	// 가능한 경우의 수: |OP|!
	// 동일한 위치에 OP가 둘 이상 저장되면, 뒤에 등장한 OP가 먼저 계산됨
	
	
	static int calc(int lhs, int rhs, char op) {
		switch(op) {
		case '+': return lhs + rhs;
		case '-': return lhs - rhs;
		case '*': return lhs * rhs;
		}
		return -1;
	}
	
	static int evalueate(List<Token> li) {
		Deque<Integer> vStk = new ArrayDeque<>();
		Deque<Character> opStk = new ArrayDeque<>();
		if(li.get(0).num == 11) {
			int a = 1;
		}
		
		for(int i = 0; i < li.size(); ++i) {
			Token token = li.get(i); 
			if(token.isNumber()) {
				vStk.offerLast(token.num);
			} else {
				if(token.op == '*') {
					while(!opStk.isEmpty() && opStk.peekLast() == '*') {
						int rhs = vStk.pollLast();
						int lhs = vStk.pollLast();
						char op = opStk.pollLast();
						vStk.offerLast(calc(lhs, rhs, op));
					}
				} else {
					while(!opStk.isEmpty()) {
						int rhs = vStk.pollLast();
						int lhs = vStk.pollLast();
						char op = opStk.pollLast();
						vStk.offerLast(calc(lhs, rhs, op));
					}
				}
				
				opStk.offerLast(token.op);
			}
		}
		while(!opStk.isEmpty()) {
			int rhs = vStk.pollLast();
			int lhs = vStk.pollLast();
			char op = opStk.pollLast();
			vStk.offerLast(calc(lhs, rhs, op));
		}
		if(vStk.peekLast() == 539) {
			int a = 1;
		}
		return vStk.pollLast();
	}
	
	static void nextSubset(int idx, String stmt, List<Token> li) {
		if(idx == N) {
			result = Math.max(result, evalueate(li));
			return;
		}
		
		// op이면 큐에 그대로 넣는다
		// num이면 그대로 넣거나(1), 바로 오른쪽 수와 연산을 한 값을 저장한다.
		char token = stmt.charAt(idx);
		if(Character.isDigit(token)) {
			li.add(new Token(token - '0'));
			nextSubset(idx + 1, stmt, li);
			li.remove(li.size() - 1);
			
			if(idx != N - 1) {
				int value = calc(stmt.charAt(idx) - '0', stmt.charAt(idx + 2) - '0', stmt.charAt(idx + 1));
				li.add(new Token(value));
				nextSubset(idx + 3, stmt, li);
				li.remove(li.size() - 1);
			}
		} else {
			li.add(new Token(token));
			nextSubset(idx + 1, stmt, li);
			li.remove(li.size() - 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stmt =  br.readLine();
		
		List<Token> li = new ArrayList<>();
		nextSubset(0, stmt, li);
		bw.append(""+result).flush();
	}
	
	
	static class Token{
		public char op;
		public int num;
		private boolean isNum;
		
		
		public Token() { }
		public Token(char op) { this.op = op; isNum = false;}
		public Token(int num) { this.num = num; isNum = true; }
		
		boolean isOperator() {return !isNum; }
		boolean isNumber() {return isNum; }
		@Override
		public String toString() {
			if(isNumber()) return "Token [num=" + num + "]";
			return "Token [op=" + op + "]";
		}
	}
}
