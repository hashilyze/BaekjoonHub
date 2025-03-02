import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {				
		String binaryString = br.readLine();
		
		int oct = 0, len = (3 - (binaryString.length() % 3)) % 3;
		for(int i = 0; i < binaryString.length(); ++i) {
			oct <<= 1;
			oct += binaryString.charAt(i) - '0';
			if(++len == 3) {
				sb.append(oct);
				oct = len = 0;
			}
		}
		
		System.out.println(sb);
	}
}