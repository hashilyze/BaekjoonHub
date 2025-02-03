import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	static final int SIZE = 5;
	
	public static void main(String[] args) throws IOException {
		String[] words = new String[SIZE];
		int[] cursors = new int[SIZE];
		for(int i = 0; i < SIZE; ++i)
			words[i] = br.readLine();
		
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			boolean stopLoop = true;
			for(int i = 0; i < SIZE; ++i) {
				if(cursors[i] < words[i].length()) {
					sb.append(words[i].charAt(cursors[i]++));
					stopLoop = false;
				}
			}
			if(stopLoop) 
				break;
		}
		
		bw.append(sb.toString());
		bw.flush();
	}
}