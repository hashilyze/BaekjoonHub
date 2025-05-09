import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		String pattern = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		while(N-- > 0) {
			String text = br.readLine(); text += text;
			if(text.contains(pattern)) ++cnt;
		}
		System.out.print(cnt);
	}
}