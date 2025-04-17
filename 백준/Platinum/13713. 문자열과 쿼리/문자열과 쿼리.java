import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 1_000_000;
	// variables 
	static String text;
	static char[] cText;
	static int M;
	static int[] z;
	
	
	static int[] getZ(char[] T) {
		int N = T.length;
		int[] z = new int[N];
		
		int l = 0, r = 0;
		for(int i = 1; i < N; ++i) {
			if(i > r) {
				l = r = i;
				while(r < N && cText[r - l] == cText[r]) ++r;
				z[i] = r - l;
				--r;
			} else {
				int k = i - l;
				
				if(z[k] < r-i+1) z[i] = z[k];
				else {
					l=i;
					while(r < N && cText[r - l] == cText[r]) ++r;
					z[i] = r - l;
					--r;
				}
			}
		}
		z[0] = N;
		return z;
	}
	
	public static void main(String[] args) throws IOException {
		text = new StringBuilder(br.readLine()).reverse().toString();
		cText = text.toCharArray();
		z = getZ(cText);
		
		M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			int i = Integer.parseInt(br.readLine());
			sb.append(z[cText.length - i]).append("\n");
		}
		System.out.print(sb);
	}
}