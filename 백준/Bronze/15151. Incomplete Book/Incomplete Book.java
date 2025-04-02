import java.io.*;
import java.util.*;

import javax.net.ssl.SSLContext;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		int k = readInt(), d = readInt();
		int s = 0, i = 0;
		while(true) {
			s += k;
			if(s > d) break;
			k <<= 1;
			i += 1;
		}
		System.out.println(i);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}