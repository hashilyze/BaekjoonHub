import java.io.*;
import java.util.*;

public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int SIZE = 1_000_000;
	// variables
	static int N, M, K;
	static long[] A = new long[SIZE];
	static long[] segment = new long[SIZE << 2];
	static long[] lazy = new long[SIZE << 2];
	
	
	static void lazyPropagate(int rangeL, int rangeR, int index) {
		if(lazy[index] != 0) {
			segment[index] += lazy[index] * (rangeR - rangeL + 1);
			if(rangeL != rangeR) {
				lazy[index * 2 + 1] += lazy[index];
				lazy[index * 2 + 2] += lazy[index];
			}
			lazy[index] = 0;
		}
	}
	static long initSegment(int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) return segment[index] = A[rangeL];
		
		int mid = (rangeL + rangeR) >> 1;
		long lhs = initSegment(rangeL, mid, index * 2 + 1);
		long rhs = initSegment(mid + 1, rangeR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	static long querySegment(int queryL, long queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return 0;
		lazyPropagate(rangeL, rangeR, index);
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
		N = readInt();
		M = readInt();

		for(int i = 0; i < N; ++i) A[i] = readInt();
		initSegment(0, N - 1, 0);
		
		for(int i = 0; i < M + K; ++i) {
			int a = readInt();
			int b = readInt();
			sb.append(querySegment(a - 1, b - 1, 0, N - 1, 0)).append("\n");
		
		}
		System.out.println(sb);
	}
	
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}