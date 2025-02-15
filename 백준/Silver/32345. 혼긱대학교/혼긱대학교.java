import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MOD = 1_000_000_007;
	// variables
	static int T;
	static boolean[] isVowel = new boolean[128];
	
	
	static int solution(String word) {
		long result = 1;
		
		int prevVowel = -1;
		for(int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);
			if(isVowel[(int)ch]) {
				if(prevVowel >= 0) {
					result *= (i - prevVowel);
					result %= MOD;
				}
				prevVowel = i;
			}
		}
		return prevVowel != -1 ? (int)result : -1;
	}
	
	public static void main(String[] args) throws IOException {
		isVowel[(int)'a'] 
			= isVowel[(int)'e'] 
			= isVowel[(int)'i'] 
			= isVowel[(int)'o']
			= isVowel[(int)'u'] 
			= true;
		
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			String word = br.readLine();
			bw.append(solution(word) + "\n");
		}
		bw.flush();
	}
}
	