import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 500_000; 
	// variables
	static int N, k, d;
	static int[] mod = new int[SIZE + 1]; // (mod K)일 때 최대 길이
	static int[] segment = new int[(SIZE + 1) << 2]; // 최댓값 
	
	
	static int querySegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return 0;
		if(queryL <= rangeL && rangeR <= queryR) return segment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = querySegment(queryL, queryR, rangeL, mid, 2*index);
		int rhs = querySegment(queryL, queryR, mid + 1, rangeR, 2*index+1);
		return Math.max(lhs, rhs);
	}
	static int updateSegment(int pos, int val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return segment[index];
		if(pos == rangeL && rangeR == pos) return segment[index] = Math.max(segment[index], val);
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = updateSegment(pos, val, rangeL, mid, 2*index);
		int rhs = updateSegment(pos, val, mid + 1, rangeR, 2*index+1);
		return segment[index] = Math.max(lhs, rhs);
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); k = readInt(); d = readInt();
		
		int max = 0;
		for(int i = 0; i < N; ++i) {
			int v = readInt();
			int len = Math.max(mod[v % k], querySegment(v-d, v+d, 1, SIZE, 1)) + 1;
			mod[v % k] = Math.max(mod[v % k], len);
			updateSegment(v, len, 1, SIZE, 1);
			
			max = Math.max(max, len);
		}
		System.out.println(max);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}