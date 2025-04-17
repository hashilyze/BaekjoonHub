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
			int k = z[i - l];
			
			if(i + k >= r) {
				k = Math.max(r - i, 0);
				while(i + k < N && cText[k] == cText[i + k]) ++k;
				l = i;
				r = i + k;
			}
			z[i] = k;
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