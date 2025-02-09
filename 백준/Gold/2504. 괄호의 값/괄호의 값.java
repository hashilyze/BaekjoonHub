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
		Deque<Character> pStk = new ArrayDeque<Character>();
		int[] vStk = new int[32];
		
		for(char ch : txt.toCharArray()) {
			if(ch == '(' || ch == '[') {
				pStk.offer(ch);
			} else if(ch == ')' || ch == ']') {
				if(pStk.isEmpty() || Math.abs(pStk.peekLast() - ch) > 2) 
					return 0;
				pStk.pollLast();
				
				int v = ch == ')' ? 2 : 3;
				if(vStk[pStk.size() + 1] > 0) {
					v *= vStk[pStk.size() + 1];
					vStk[pStk.size() + 1] = 0;
				}
				vStk[pStk.size()] += v;
			}
		}
		return pStk.isEmpty() ? vStk[0] : 0;
	}
}