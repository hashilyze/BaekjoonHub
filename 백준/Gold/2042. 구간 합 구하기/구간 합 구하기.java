import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int SIZE = 1_000_000;
	// variables
	static int N, M, K;
	static long[] arr = new long[SIZE];
	static long[] segment = new long[SIZE << 2];
	
	
	static long initSegment(int queryL, int queryR, int index) {
		if(queryL == queryR) return segment[index] = arr[queryL];
		
		int mid = (queryL + queryR) >> 1;
		long lhs = initSegment(queryL, mid, index * 2 + 1);
		long rhs = initSegment(mid + 1, queryR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	
	static long querySegment(int queryL, int queryR, int index, int segmentL, int segmentR) {
		if(segmentR < queryL || queryR < segmentL) return 0L;
		if(queryL <= segmentL && segmentR <= queryR) return segment[index];
		
		int mid = (segmentL + segmentR) >> 1;
		long lhs = querySegment(queryL, queryR, index * 2 + 1, segmentL, mid);
		long rhs = querySegment(queryL, queryR, index * 2 + 2, mid + 1, segmentR);
		return lhs + rhs;
	}
	
	static long updateSegment(int pos, long val, int index, int segmentL, int segmentR) {
		if(pos < segmentL || segmentR < pos) return segment[index];
		if(segmentL == segmentR && segmentL == pos) return segment[index] = arr[pos] = val;
		
		int mid = (segmentL + segmentR) >> 1;
		long lhs = updateSegment(pos, val, index * 2 + 1, segmentL, mid);
		long rhs = updateSegment(pos, val, index * 2 + 2, mid + 1, segmentR);
		return segment[index] = lhs + rhs;
	}
	
	public static void main(String[] args) throws IOException {
		N = (int)readLong();
		M = (int)readLong();
		K = (int)readLong();
		for(int i = 0; i < N; ++i) arr[i] = readLong();
		
		initSegment(0, N - 1, 0);
		for(int i = 0; i < M + K; ++i) {
			int a = (int)readLong();
			long b = readLong(), c = readLong();
			if(a == 1) {
				updateSegment((int)b - 1, c, 0, 0, N - 1);
			} else {
				sb.append(querySegment((int)b - 1, (int)c - 1, 0, 0, N - 1))
					.append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	// 정수 입력 
	static long readLong() throws IOException {
		long c, n = 0, s = 1;
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