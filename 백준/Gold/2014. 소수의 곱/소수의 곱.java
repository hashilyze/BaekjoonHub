import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static int MAX_K = 100;
	// variables
	static int K, N;
	static int[] primes = new int[MAX_K];
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		// Input
		K = readInt();
		N = readInt();
		for(int i = 0; i < K; ++i) minHeap.offer(primes[i] = readInt());
		
		// Solution
		while(--N > 0) {
			int val = minHeap.poll();
			for(int i = 0; i < K; ++i) {
				long next = (long)primes[i] * val;
				if(next > Integer.MAX_VALUE) break;
				
				minHeap.offer((int)next);
				if(val % primes[i] == 0) break;
			}
		}
		System.out.println(minHeap.poll());
		
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
