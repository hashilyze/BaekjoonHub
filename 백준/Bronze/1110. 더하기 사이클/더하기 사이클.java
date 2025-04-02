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
		int cnt = 0;
		int origin = readInt();
		int value = origin;
		while(true){
			int sum = 0;
			String s = Integer.toString(value);
			for(int digit : s.toCharArray()) sum += digit - '0';
			value = (value % 10) * 10 + sum % 10;
			++cnt;
			
			if(origin == value) break;
		}
		System.out.println(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		return n;
	}
}