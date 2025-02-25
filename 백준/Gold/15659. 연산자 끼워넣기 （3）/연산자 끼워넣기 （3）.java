import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Token{
		char op;
		int num;
		
		Token(int num) { this.num = num; }
		Token(char op) { this.op = op; }
	};
	
	static final int MAX_N = 11;
	static final int NUM_OP = 4;
	static final char[] I2C = new char[] {'+', '-', '*', '/'};
	
	static int N;
	static int[] numbers = new int[MAX_N];
	static int[] operators = new int[NUM_OP];
	
	
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	static List<Token> exp = new ArrayList<>(2 * MAX_N - 1);
	
	
	static int calc(int lhs, int rhs, char op) {
		switch (op) {
		case '+': return lhs + rhs;
		case '-': return lhs - rhs;
		case '*': return lhs * rhs;
		case '/': return lhs / rhs;
		default: throw new IllegalArgumentException("Unexpected value");
		}
	}
	
	static void consumeOp(Deque<Integer> vStk, Deque<Character> opStk) {
		int rhs = vStk.pollLast();
		int lhs = vStk.pollLast();
		char op = opStk.pollLast();
		vStk.offerLast(calc(lhs, rhs, op));
	}
	
	static int evaluate() {
		Deque<Integer> vStk = new ArrayDeque<>();
		Deque<Character> opStk = new ArrayDeque<>();
		
		for(Token token : exp) {
			if(token.op == 0) {
				vStk.offerLast(token.num);
			} else {
				if(token.op == '+' || token.op == '-') {
					while(!opStk.isEmpty()) {
						consumeOp(vStk, opStk);
					}
				} else {
					while(!opStk.isEmpty() && (opStk.peekLast() == '*' || opStk.peekLast() == '/')) {
						consumeOp(vStk, opStk);
					}
				}
				opStk.offerLast(token.op);
			}
		}
		while(!opStk.isEmpty()) {
			consumeOp(vStk, opStk);
		}
		
		return vStk.pollFirst();
	}
	
	static void generatePermutation(int idx) {
		if(idx == N) {
			int eval = evaluate();
			min = Math.min(min, eval);
			max = Math.max(max, eval);
			return;
		}
		for(int i = 0; i < NUM_OP; ++i) {
			if(operators[i] == 0) continue;
			--operators[i];
			exp.add(new Token(I2C[i]));
			exp.add(new Token(numbers[idx]));
			generatePermutation(idx + 1);
			exp.remove(exp.size() - 1);
			exp.remove(exp.size() - 1);
			++operators[i];
		}
	}
	
	static void solution() {
		exp.add(new Token(numbers[0]));
		generatePermutation(1);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < NUM_OP; ++i) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		solution();
		System.out.println(max);
		System.out.println(min);
	}
}