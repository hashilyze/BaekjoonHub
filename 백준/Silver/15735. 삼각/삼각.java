import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		int N = readInt();
		
		System.out.print(((long)N * (N + 2) * (2 * N + 1)) / 8);
	}
	
	// 음수와 부호없는 정수를 읽음
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}