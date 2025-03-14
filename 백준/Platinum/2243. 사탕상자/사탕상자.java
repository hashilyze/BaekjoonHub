import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1_000_010;
	// variables
	static int N;
	static int[] arr = new int[SIZE];
	static int[] segment = new int[SIZE << 2];
	
	
	static int updateSegment(int pos, int val, int rangeL, int rangeR, int index) {
		if(rangeR < pos || pos < rangeL) return segment[index];
		if(pos == rangeL && pos == rangeR) return segment[index] = arr[pos] = val;
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = updateSegment(pos, val, rangeL, mid, index * 2 + 1);
		int rhs = updateSegment(pos, val, mid + 1, rangeR, index * 2 + 2);
		return segment[index] = lhs + rhs;
	}
	static int nthElement(int nth, int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) return rangeL;
		
		int mid = (rangeL + rangeR) >> 1;
		int leftSum = segment[index * 2 + 1]; 
		if(nth <= leftSum) return nthElement(nth, rangeL, mid, index * 2 + 1);
		else return nthElement(nth - leftSum, mid + 1, rangeR, index * 2 + 2);
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		while(N-- > 0) {
			int a = readInt(), b = readInt();
			if(a == 1) { // 사탕 꺼내기
				int taste = nthElement(b, 1, SIZE - 1, 0);
				updateSegment(taste, arr[taste] - 1, 1, SIZE - 1, 0);
				sb.append(taste).append("\n");
			} else {
				int c = readInt();
				updateSegment(b, arr[b] + c, 1, SIZE - 1, 0);
			}
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
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