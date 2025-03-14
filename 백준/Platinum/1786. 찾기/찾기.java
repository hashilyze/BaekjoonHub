import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static String T, P;
	static int[] pi;
	static List<Integer> ans = new ArrayList<>();
	
	static void makePi() {
		int n = P.length();
		pi = new int[n];
		
		int matched = 0;
		for(int next = 1; next < n; ++next) {
			while(matched > 0 && P.charAt(next) != P.charAt(matched)) matched = pi[matched - 1];
			if(P.charAt(next) == P.charAt(matched)) pi[next] = ++matched;
		}
	}
	
	static void kmp() {
		int txtLength = T.length(), patLength = P.length();
		makePi();
		int matched = 0;
		for(int next = 0; next < txtLength; ++next) {
			while(matched > 0 && T.charAt(next) != P.charAt(matched)) matched = pi[matched - 1];
			if(T.charAt(next) == P.charAt(matched) && ++matched == patLength) {
				ans.add(next - matched + 1);
				matched = pi[matched - 1];
			}
		}
	}

	
	public static void main(String[] args) throws IOException {
		// Input
		T = br.readLine();
		P = br.readLine();
		
		kmp();
		// Output
		sb.append(ans.size()).append("\n");
		for(int pos : ans) sb.append(pos+1).append(" ");
		System.out.print(sb);
	}
}