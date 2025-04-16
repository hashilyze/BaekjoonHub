import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static String sText;
	static char[] text, prefix, suffix;
	static List<Integer> ans = new ArrayList<>();
	
	static int[] getLps(char[] text) {
		int txtLength = text.length;
		int[] lps = new int[text.length];
		
		int matched = 0;
		for(int next = 1; next < txtLength; ++next) {
			while(matched > 0 && text[next] != text[matched]) matched = lps[matched - 1];
			if(text[next] == text[matched]) lps[next] = ++matched;
		}
		return lps;
	}
	
	static int kmp(char[] text, char[] prefix, char[] suffix) {
		int txtLength = text.length, pLength = prefix.length, sLength = suffix.length;
		int[] pLps = getLps(prefix);
		int[] sLps = getLps(suffix);
		
		List<Integer> li = new ArrayList<>();
		Set<String> s = new HashSet<String>();
		
		int pMatched = 0, sMatched = 0;
		for(int next = 0; next < txtLength; ++next) {
			while(pMatched > 0 && text[next] != prefix[pMatched]) pMatched = pLps[pMatched - 1];
			while(sMatched > 0 && text[next] != suffix[sMatched]) sMatched = sLps[sMatched - 1];
			
			if(text[next] == prefix[pMatched]) {
				if(++pMatched == pLength) {
					pMatched = pLps[pMatched - 1];
					li.add(next - pLength + 1);
				}
			}
			if(text[next] == suffix[sMatched]) {
				if(++sMatched == sLength) {
					sMatched = sLps[sMatched - 1];
					for(int beg : li) {
						int len = next + 1 - beg;
						if(len < pLength || len < sLength) continue;
						s.add(sText.substring(beg, next+1));
					}
					
				}
			}
		}
		return s.size();
	}
	
	public static void main(String[] args) throws IOException {
		text = (sText = br.readLine()).toCharArray();
		prefix = br.readLine().toCharArray();
		suffix = br.readLine().toCharArray();
		
		System.out.print(kmp(text, prefix, suffix));
		
	}
}