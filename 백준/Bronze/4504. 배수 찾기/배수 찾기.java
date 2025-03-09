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
		int n = readInt(), m;
		while((m = readInt()) != 0) {
			if(m % n == 0) sb.append(String.format("%d is a multiple of %d.\n", m, n));
			else sb.append(String.format("%d is NOT a multiple of %d.\n", m, n));
		}
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}