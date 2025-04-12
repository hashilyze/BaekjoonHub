import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1_000_000; 
	// variables
	static int N, M, K;
	static long[] A = new long[SIZE];
	static long[] segment = new long[SIZE << 2];
	
	
	static long initSegment(int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) return segment[index] = A[rangeL];
		int mid = (rangeL + rangeR) >> 1;
		long lhs = initSegment(rangeL, mid, (index << 1) + 1);
		long rhs = initSegment(mid + 1, rangeR, (index << 1) + 2);
		return segment[index] = lhs + rhs; 
	}
	static long querySegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return 0;
		if(queryL <= rangeL && rangeR <= queryR) return segment[index];
		int mid = (rangeL + rangeR) >> 1;
		long lhs = querySegment(queryL, queryR, rangeL, mid, (index << 1) + 1);
		long rhs = querySegment(queryL, queryR, mid + 1, rangeR, (index << 1) + 2);
		return lhs + rhs;
	}
	static long updateSegment(int pos, long val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return segment[index];
		if(pos == rangeL && rangeR == pos) return segment[index] = A[rangeL] = val;
		int mid = (rangeL + rangeR) >> 1;
		long lhs = updateSegment(pos, val, rangeL, mid, (index << 1) + 1);
		long rhs = updateSegment(pos, val, mid + 1, rangeR, (index << 1) + 2);
		return segment[index] = lhs + rhs;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		N = (int)readLong();
		M = (int)readLong();
		K = (int)readLong();
		for(int i = 0; i < N; ++i) A[i] = readLong();
		
		initSegment(0, N - 1, 0);
		for(int q = 0; q < M + K; ++q) {
			long a = readLong(), b = readLong(), c = readLong();
			if(a == 1) {
				updateSegment((int)b - 1, c, 0, N - 1, 0);
			} else { // a== 2
				sb.append(querySegment((int)b - 1, (int)c - 1, 0, N - 1, 0)).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static long readLong() throws IOException {
		long c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n= (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}