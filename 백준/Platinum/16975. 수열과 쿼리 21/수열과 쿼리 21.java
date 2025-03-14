import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 100_010; 
	// variables
	static int N, M;
	static long[] arr = new long[SIZE];
	static long[] segment = new long[SIZE << 2];
	static long[] lazy = new long[SIZE << 2];
	
	
	static void lazyPropagate(int rangeL, int rangeR, int index) {
		if(lazy[index] == 0) return;
		segment[index] += lazy[index] * (rangeR - rangeL + 1);
		if(rangeL != rangeR) { 
			lazy[index * 2 + 1] += lazy[index];
			lazy[index * 2 + 2] += lazy[index];
		} 
		lazy[index] = 0;
	}
	
	static long initSegment(int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) return segment[index] = arr[rangeL];
		int mid = (rangeL + rangeR) >> 1;
		long lhs = initSegment(rangeL, mid, index * 2 + 1);
		long rhs = initSegment(mid + 1, rangeR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	
	static long querySegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		lazyPropagate(rangeL, rangeR, index);
		if(rangeR < queryL || queryR < rangeL) return 0;
		if(queryL <= rangeL && rangeR <= queryR) return segment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		long lhs = querySegment(queryL, queryR, rangeL, mid, index * 2 + 1);
		long rhs = querySegment(queryL, queryR, mid + 1, rangeR, index * 2 + 2);
		return lhs + rhs;
	}
	
	static long updateSegment(int updateL, int updateR, long diff, int rangeL, int rangeR, int index) {
		lazyPropagate(rangeL, rangeR, index);
		if(rangeR < updateL || updateR < rangeL) return segment[index];
		if(updateL <= rangeL && rangeR <= updateR) {
			lazy[index] += diff;
			lazyPropagate(rangeL, rangeR, index);
			return segment[index];
		}
		
		int mid = (rangeL + rangeR) >> 1;
		long lhs = updateSegment(updateL, updateR, diff, rangeL, mid, index * 2 + 1);
		long rhs = updateSegment(updateL, updateR, diff, mid + 1, rangeR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 0; i < N; ++i) arr[i] = readInt();
		initSegment(0, N - 1, 0);
		
		M = readInt();
		for(int i = 0; i < M; ++i) {
			int cmd = readInt();
			if(cmd == 1) {
				int l = readInt(), r = readInt(), d = readInt();
				updateSegment(l - 1, r - 1, d, 0, N - 1, 0);
			} else {
				int p = readInt();
				sb.append(querySegment(p - 1, p - 1, 0, N - 1, 0)).append("\n");
			}
		}
		// Output
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n * s;
	}
}