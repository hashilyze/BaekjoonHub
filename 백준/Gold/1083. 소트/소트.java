import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		int value;
		int rank;
	}
	// constants
	// variables
	static int N, S;
	static Node[] A;
	
	static void solution() {
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < i; ++j) {
				if(A[j].value < A[i].value) ++A[i].rank;
			}
		}
		
		int rankSum = 0;
		for(int i = 0; i < N; ++i) rankSum += A[i].rank;
		
		while(S > 0 && rankSum > 0) {
			// 가장 높은 자릿수를 바꿀 수 있는 수를 택한다.
			int tgt = -1;
			for(int i = 0; i < N; ++i) {
				if(A[i].rank == 0) continue;
				
				int lPos, rPos;
				if(tgt == -1
					|| (lPos = i - Math.min(A[i].rank, S)) < (rPos = tgt - A[tgt].rank)
					|| lPos == rPos && A[i].value > A[tgt].value
				) {
					tgt = i;
				}
			}
			
			int swapCnt = Math.min(A[tgt].rank, S);
			rankSum -= swapCnt;
			S -= swapCnt;
			A[tgt].rank = 0;
			
			while(swapCnt-- > 0) {
				Node tmp = A[tgt]; A[tgt] = A[tgt - 1]; A[tgt - 1] = tmp;
				--tgt;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		A = new Node[N];
		for(int i = 0; i < N; ++i) {
			A[i] = new Node();
			A[i].value = readInt();
		}
		S = readInt();
		
		solution();
		
		for(int i = 0; i < N; ++i) sb.append(A[i].value).append(' ');
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}