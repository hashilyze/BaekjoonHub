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
	static int SIZE = 100_000;
	// variables
	static int N, M;
	static int[] arr = new int[SIZE];
	static int[][] segment = new int[SIZE << 2][2];
	
	
	static int[] initSegment(int segmentL, int segmentR, int index) {
		if(segmentL == segmentR) return segment[index] = new int[] {arr[segmentL], arr[segmentR]};
		int mid = (segmentL + segmentR) >> 1;
		int[] lhs = initSegment(segmentL, mid, index * 2 + 1);
		int[] rhs = initSegment(mid + 1, segmentR, index * 2 + 2);
		return segment[index] = new int[] { Math.min(lhs[0], rhs[0]), Math.max(lhs[1], rhs[1]) };
	}
	
	static int[] querySegment(int queryL, int queryR, int segmentL, int segmentR, int index) {
		if(segmentR < queryL || queryR < segmentL) return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
		if(queryL <= segmentL && segmentR <= queryR) return segment[index];
		
		int mid = (segmentL + segmentR) >> 1;
		int[] lhs = querySegment(queryL, queryR, segmentL, mid, index * 2 + 1);
		int[] rhs = querySegment(queryL, queryR, mid + 1, segmentR, index * 2 + 2);
		return new int[] { Math.min(lhs[0], rhs[0]), Math.max(lhs[1], rhs[1]) };
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i < N; ++i) arr[i] = readInt();
		
		initSegment(0, N - 1, 0);
		for(int i = 0; i < M; ++i) {
			int a = readInt(), b = readInt();
			int[] ans = querySegment(a - 1, b - 1, 0, N - 1, 0); 
			sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
		}
		System.out.print(sb);
		
	}
	
	// 정수 입력 
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}