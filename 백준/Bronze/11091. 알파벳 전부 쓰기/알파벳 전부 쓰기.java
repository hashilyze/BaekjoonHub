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
	
	public static void main(String[] args) throws IOException {
		int N = readInt();
		while(N-- > 0) {
			int used = 0;
			
			for(char ch : br.readLine().toCharArray()) {
				if(ch >= 0x61) ch -= 0x20;
				if(ch >= 0x41) {
					used |= 0x01 << (ch - 0x41);
				}
			}
			
			if(used == 0x3FFFFFF) {
				sb.append("pangram\n");
			} else {
				sb.append("missing ");
				for(int i = 0; i < 26; ++i) {
					if((used & (0x01 << i)) == 0) sb.append((char)('a' + i));
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}