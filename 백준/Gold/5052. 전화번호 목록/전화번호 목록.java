import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 10_000;	// 단어의 수
	static final int LENGTH = 10;	// 단어의 최대 길이
	static final int RANGE = 10;	// 문자의 수
	static final int ROOT = 0;
	// types
	static class Node{
		int[] nexts = new int[RANGE];
		boolean isTerminal = false;
	}
	// variables
	static int size = 0;
	static Node[] pool = new Node[SIZE * LENGTH + 1];
	
	
	static boolean insert(char[] word) {
		Node cur = pool[ROOT];
		for(char ch : word){
			int digit = ch & 0x0F;
			if(cur.nexts[digit] == 0) {
				cur.nexts[digit] = ++size;
				pool[size] = new Node();
			}
			// 이미 트라이에 존재하어 단어가, 현재 삽입하는 단어의 접두사인지 확인
			if(pool[cur.nexts[digit]].isTerminal) return false;
			
			cur = pool[cur.nexts[digit]];
		}
		
		cur.isTerminal = true;
		// 현재 삽입하는 단어가 이미 트라이에 존재하는 단어의 접두사인지 확인
		return pool[size] == cur; 
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			// 초기화
			size = 0;
			pool[ROOT] = new Node();
			
			int N = Integer.parseInt(br.readLine());
			boolean pass = true;
			while(N-- > 0) {
				char[] word = br.readLine().toCharArray();
				pass = pass && insert(word);
			}
			sb.append(pass ? "YES\n" : "NO\n");
			
			Arrays.fill(pool, 0, N + 1, null);
		}
		System.out.print(sb);
	}
}
