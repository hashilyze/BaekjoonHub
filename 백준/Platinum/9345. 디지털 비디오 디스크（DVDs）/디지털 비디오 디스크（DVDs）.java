import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 100_100;
	// variables
	static int N, K;
	static int[] arr = new int[SIZE];
	static int[] minSegment = new int[SIZE << 2];
	static int[] maxSegment = new int[SIZE << 2];
	
	
	static void initSegment(int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) {
			minSegment[index] = maxSegment[index] = arr[rangeL];
			return;
		}
		
		int mid = (rangeL + rangeR) >> 1;
		initSegment(rangeL, mid, index * 2 + 1);
		initSegment(mid + 1, rangeR, index * 2 + 2);
		
		minSegment[index] = Math.min(minSegment[index * 2 + 1], minSegment[index * 2 + 2]);
		maxSegment[index] = Math.max(maxSegment[index * 2 + 1], maxSegment[index * 2 + 2]);
	}
	static int queryMinSegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return Integer.MAX_VALUE;
		if(queryL <= rangeL && rangeR <= queryR) return minSegment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = queryMinSegment(queryL, queryR, rangeL, mid, index * 2 + 1);
		int rhs = queryMinSegment(queryL, queryR, mid + 1, rangeR, index * 2 + 2);
		return Math.min(lhs, rhs);
	}
	static int queryMaxSegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return Integer.MIN_VALUE;
		if(queryL <= rangeL && rangeR <= queryR) return maxSegment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = queryMaxSegment(queryL, queryR, rangeL, mid, index * 2 + 1);
		int rhs = queryMaxSegment(queryL, queryR, mid + 1, rangeR, index * 2 + 2);
		return Math.max(lhs, rhs);
	}
	static int updateMinSegment(int pos, int val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return minSegment[index];
		if(pos == rangeL && pos == rangeR) return minSegment[index] = arr[rangeL] = val;
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = updateMinSegment(pos, val, rangeL, mid, index * 2 + 1);
		int rhs = updateMinSegment(pos, val, mid + 1, rangeR, index * 2 + 2);
		return minSegment[index] =  Math.min(lhs, rhs);
	}
	static int updateMaxSegment(int pos, int val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return maxSegment[index];
		if(pos == rangeL && pos == rangeR) return maxSegment[index] = arr[rangeL] = val;
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = updateMaxSegment(pos, val, rangeL, mid, index * 2 + 1);
		int rhs = updateMaxSegment(pos, val, mid + 1, rangeR, index * 2 + 2);
		return maxSegment[index] =  Math.max(lhs, rhs);
	}
	
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			N = readInt(); K = readInt();
			// 초기 상태: 모든 책이 순서대로 나열됨
			for(int i = 0; i < N; ++i) arr[i] = i;
			initSegment(0, N - 1, 0);
			
			while(K-- > 0) {
				int Q = readInt(), A = readInt(), B = readInt();
				
				if(Q == 0) { // swap
					int tmpA = arr[A], tmpB = arr[B]; // A번째 책과 B번째 책을 스퇍
					
					updateMinSegment(A, tmpB, 0, N - 1, 0);
					updateMinSegment(B, tmpA, 0, N - 1, 0);
					
					updateMaxSegment(A, tmpB, 0, N - 1, 0);
					updateMaxSegment(B, tmpA, 0, N - 1, 0);
				} else { // (Q == 1); query
					int max = queryMaxSegment(A, B, 0, N - 1, 0);
					int min = queryMinSegment(A, B, 0, N - 1, 0);
					// 서로 다른 N개의 수가 연속할 때, (최댓값 - 최솟값 + 1) == N을 만족한다.
					sb.append(A == min && B == max ? "YES\n" : "NO\n");
				}
			}
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		return n;
	}
}