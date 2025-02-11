import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static String solution() throws IOException {
		String maxWord = "";
		while(true) {
			String line = br.readLine();
			line = line.replaceAll("[^a-zA-Z-]", " ");
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()) {
				String word = st.nextToken();
				if(word.equals("E-N-D")) return maxWord.toLowerCase();
				
				if(maxWord.length() < word.length()) {
					maxWord = word;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		bw.append(solution()).flush();
	}
}
	