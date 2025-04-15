import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int MAX_N = 50;
	static int SIZE = 1001;
	// variables
	static int N;
	static int[] B = new int[MAX_N];
	static int[] counts = new int[SIZE];
	static int[] P = new int[MAX_N];
	
	
	static void solution() {
		for(int i = 0; i < N; ++i) ++counts[B[i]];
		for(int i = 1; i < SIZE; ++i) counts[i] += counts[i - 1];
		for(int i = N-1; i >= 0; --i) P[i] = --counts[B[i]];
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) B[i] = readInt();
		solution();
		for(int i = 0; i < N; ++i) sb.append(P[i]).append(" ");
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}