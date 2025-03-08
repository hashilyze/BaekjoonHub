import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node{
		int pos;
		long sum;
		Node(int pos, long sum) { this.pos = pos; this.sum = sum; }
	}
	// constants
	// variables
	static int N, M;
	static int[] A = new int[50];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i < N; ++i) A[i] = i + 1;
		
		int sum = 0;
		int at = 0;
		for(int i = 0; i < M; ++i) {
			while(A[at] == 0) at = (at + 1 + N) % N;
			int tgt = readInt();
			
			int lcur = 0, rcur = 0;
			int lcnt = 0, rcnt = 0;
			while(tgt != A[(at - lcur + N) % N]) {
				if(A[(at - lcur + N) % N] != 0) 
					++lcnt;
				++lcur;
			}
			while(tgt != A[(at + rcur) % N]) {
				if(A[(at + rcur) % N] != 0) 
					++rcnt;
				++rcur;	
			}
			
			sum += Math.min(lcnt, rcnt);
			at = tgt - 1;
			A[at] = 0;
		}
		System.out.print(sum);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}