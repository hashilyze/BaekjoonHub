import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 3;
	// variables
	static int N;
	static int[] dp = new int[SIZE];
	
	static void update(int r, int g, int b) {
		r += Math.min(dp[1], dp[2]);
		g += Math.min(dp[2], dp[0]);
		b += Math.min(dp[0], dp[1]);
		dp[0] = r;
		dp[1] = g;
		dp[2] = b;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		while(N-- > 0) {
			update(readInt(), readInt(), readInt());
		}
		System.out.print(Math.min(Math.min(dp[0], dp[1]), dp[2]));
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