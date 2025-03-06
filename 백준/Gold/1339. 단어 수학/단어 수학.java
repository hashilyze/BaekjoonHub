import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Alphabet{
		int ch; // 알파벳
		long priority; // 각 자릿수에서 등장 빈도
		int value; // 최종 할당된 값
		
		@Override
		public String toString() {
			return "Alphabet [ch=" + ch + ", priority=" + priority + ", value=" + value + "]";
		}

		public Alphabet(int ch) { this.ch = ch; }
	};
	// constants
	static int MAX_N = 10;
	static int NUM_ALPHABET = 26;
	static int NUM_DIGIT = 10;
	// variables
	static int N;
	static String[] words = new String[MAX_N];
	static Alphabet[] alphabets = new Alphabet[NUM_ALPHABET];
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < NUM_ALPHABET; ++i) alphabets[i] = new Alphabet('A' + i);
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			words[i] = br.readLine();
			
			int factor = 1;
			for(int j = words[i].length() - 1; j >= 0; --j) {
				char ch = words[i].charAt(j);
				alphabets[ch - 'A'].priority += factor;
				factor *= 10;
			}
		}
		
		Arrays.sort(alphabets, (lhs, rhs)->rhs.priority - lhs.priority < 0 ? -1 : 1);
		for(int i = 0; i < NUM_DIGIT; ++i) alphabets[i].value = NUM_DIGIT - 1 - i;
		Arrays.sort(alphabets, (lhs, rhs)->lhs.ch - rhs.ch);
		
		long sum = 0L;
		for(int i = 0; i < N; ++i) {
			long value = 0;
			for(char ch : words[i].toCharArray()) {
				value = value * 10 + alphabets[ch - 'A'].value;
			}
			sum += value;
		}
		System.out.println(sum);
	}
}