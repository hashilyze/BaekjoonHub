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
	static int N, L, R, X;
	static int[] A = new int[15];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		L = readInt();
		R = readInt();
		X = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		int cnt = 0;
		for(int bits = 0x01, limit = 0x01 << N; bits < limit; ++bits) {
			int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			for(int i = 0; i < N; ++i) {
				if((bits & (0x01 << i)) != 0) {
					sum += A[i];
					min = Math.min(min, A[i]);
					max = Math.max(max, A[i]);
				}
			}
			if(L <= sum && sum <= R && max - min >= X) {
				++cnt;
			}
		}
		System.out.println(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}