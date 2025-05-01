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
	static final int[] DEFAULT_NEXTS = new int[RANGE];
	// types
	// variables
	static int size = 0;
	static int[][] nexts = new int[SIZE * LENGTH + 1][RANGE];
	static boolean[] isTerminals = new boolean[SIZE * LENGTH + 1];
	
	static boolean insert(char[] word) {
		int cur = ROOT;
		for(char ch : word){
			int digit = ch & 0x0F;
			if(nexts[cur][digit] == 0) {
				nexts[cur][digit] = ++size;
			}
			// 이미 트라이에 존재하어 단어가, 현재 삽입하는 단어의 접두사인지 확인
			if(isTerminals[nexts[cur][digit]]) return false;
			
			cur = nexts[cur][digit];
		}
		
		isTerminals[cur] = true;
		// 현재 삽입하는 단어가 이미 트라이에 존재하는 단어의 접두사인지 확인
		return size == cur; 
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			// 초기화
			size = 0;
			
			int N = Integer.parseInt(br.readLine());
			boolean pass = true;
			while(N-- > 0) {
				char[] word = br.readLine().toCharArray();
				pass = pass && insert(word);
			}
			sb.append(pass ? "YES\n" : "NO\n");
			
			for(int i = 0; i <= size; ++i) System.arraycopy(DEFAULT_NEXTS, 0, nexts[i], 0, RANGE); 
			Arrays.fill(isTerminals, 0, size+1, false);
		}
		System.out.print(sb);
	}
}
