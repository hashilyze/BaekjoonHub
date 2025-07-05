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
	
	
	static int solution(int x, int y) {
		int cnt = 0;
		
		int amount = 1;
		int diff = y - x;
		while(diff > 0) {
			diff = Math.max(0, diff - amount);
			++cnt;
			
			if(diff > 0) {
				diff = Math.max(0, diff - amount);
				++cnt;
			}
			++amount;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			int x = readInt();
			int y = readInt();
			sb.append(solution(x, y)).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}