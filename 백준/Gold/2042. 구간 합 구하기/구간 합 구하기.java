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
	static int SIZE = 1_000_000 + 10;
	// variables
	static int N, M, K;
	static long[] arr = new long[SIZE];
	static long[] seg = new long[SIZE << 2];
	
	
	static long initSegment(int left, int right, int index) {
		if(left == right) return seg[index] = arr[left];
		
		int mid = (left + right) >> 1;
		long lhs = initSegment(left, mid, index * 2 + 1);
		long rhs = initSegment(mid + 1, right, index * 2 + 2);
		return seg[index] = lhs + rhs;
	}
	static long querySegment(int left, int right, int index, int lo, int  hi) {
		if(hi < left || right < lo) return 0;
		if(left <= lo && hi <= right) return seg[index];
		
		int mid = (lo + hi) >> 1;
		long lhs = querySegment(left, right, index * 2 + 1, lo, mid);
		long rhs = querySegment(left, right, index * 2 + 2, mid + 1, hi);
		return lhs + rhs;
	}
	static long updateSegment(int pos, long val, int index, int lo, int hi) {
		if(pos < lo || hi < pos) return seg[index];
		if(pos == lo && hi == pos) return seg[index] = arr[pos] = val;
		
		int mid = (lo + hi) >> 1;
		long lhs = updateSegment(pos, val, index * 2 + 1, lo, mid);
		long rhs = updateSegment(pos, val, index * 2 + 2, mid + 1, hi);
		return seg[index] = lhs + rhs;
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
			
			switch(a) {
			case 1: updateSegment((int)b - 1, c, 0, 0, N - 1); break;
			case 2: sb.append(querySegment((int)b - 1, (int)c - 1, 0, 0, N - 1)).append("\n"); break;
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