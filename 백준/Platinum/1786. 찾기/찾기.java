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
	static char[] T, P;
	static List<Integer> ans = new ArrayList<>();
	
	static int[] getPi() {
		int n = P.length;
		int[] pi = new int[n];
		
		int matched = 0;
		for(int next = 1; next < n; ++next) {
			while(matched > 0 && P[next] != P[matched]) matched = pi[matched - 1];
			if(P[next] == P[matched]) pi[next] = ++matched;
		}
		return pi;
	}
	
	static void kmp() {
		int txtLength = T.length, patLength = P.length;
		int[] pi = getPi();
		
		int matched = 0;
		for(int next = 0; next < txtLength; ++next) {
			while(matched > 0 && T[next] != P[matched]) matched = pi[matched - 1];
			if(T[next] == P[matched] && ++matched == patLength) {
				ans.add(next - matched + 1);
				matched = pi[matched - 1];
			}
		}
	}

	
	public static void main(String[] args) throws IOException {
		// Input
		T = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		
		kmp();
		// Output
		sb.append(ans.size()).append("\n");
		for(int pos : ans) sb.append(pos+1).append(" ");
		System.out.print(sb);
	}
}