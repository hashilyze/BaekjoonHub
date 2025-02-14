import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;	
	// constants
	// variables
	static int N;
	static String stmt;
	static int result = Integer.MIN_VALUE;
	
	
	static int calc(int lhs, int rhs, char op) {
		switch(op) {
		case '+': return lhs + rhs;
		case '-': return lhs - rhs;
		case '*': return lhs * rhs;
		}
		return 0;
	}
	
	static void nextSubset(int idx, String stmt, int leftNum, char leftOp) {
		if(idx >= N) {
			result = Math.max(result, leftNum);
			return;
		}
		
		int num1 = stmt.charAt(idx) - '0';
		nextSubset(idx + 2, stmt, calc(leftNum, num1, leftOp) , stmt.charAt(idx + 1));
		if(idx != N - 1) {
			int num2 = stmt.charAt(idx + 2) - '0';
			int concat = calc(num1, num2, stmt.charAt(idx + 1));
			nextSubset(idx + 4, stmt, calc(leftNum, concat, leftOp), stmt.charAt(idx + 3));
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stmt =  br.readLine() + " ";
		
		nextSubset(0, stmt, 0, '+');
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
