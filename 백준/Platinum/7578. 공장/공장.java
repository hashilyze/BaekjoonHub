import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1_000_001;
	// variables
	static int N;
	static int[] idToPos = new int[SIZE]; // A열에 위치한 식별번호가 i인 기계의 위치 
	static long[] segment = new long[SIZE << 4];
	
	static long querySegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return 0L;
		if(queryL <= rangeL && rangeR <= queryR) return segment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		long lhs = querySegment(queryL, queryR, rangeL, mid, index * 2 + 1);
		long rhs = querySegment(queryL, queryR, mid + 1, rangeR, index * 2 + 2);
		return lhs + rhs;
	}
	static long updateSegment(int pos, long val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return segment[index];
		if(pos == rangeL && pos == rangeR) return segment[index] = val;
		
		int mid = (rangeL + rangeR) >> 1;
		long lhs = updateSegment(pos, val, rangeL, mid, index * 2 + 1);
		long rhs = updateSegment(pos, val, mid + 1, rangeR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			idToPos[readInt()] = i;
		}
		
		long cnt = 0;
		for(int i = 0; i < N; ++i) {
			int id = readInt();
			cnt += querySegment(idToPos[id] + 1, SIZE - 1, 0, SIZE - 1, 0);
			updateSegment(idToPos[id], 1, 0, SIZE - 1, 0);
		}
		System.out.println(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}