import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static int[] X;
	static int[] leftSum, rightSum;
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		X = new int[N];
		for(int i = 0; i < N; ++i) X[i] = readInt();
		
		leftSum = new int[N];
		rightSum = new int[N];
		
		leftSum[0] = leftSum[1] = X[0];
		for(int i = 2; i < N - 1; i += 2) {
			leftSum[i] = leftSum[i + 1] = leftSum[i - 2] + X[i]; 
		}
		rightSum[0] = rightSum[1] = X[N - 1];
		for(int i = 2; i < N - 1; i += 2) {
			rightSum[i] = rightSum[i + 1] = rightSum[i - 2] + X[N - 1 - i]; 
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; ++i) {
			int sum = 0; 
			if(i - 1 >= 0) sum += leftSum[i - 1];
			if(N - i - 1 >= 0) sum += rightSum[N - i - 1];
			if(i % 2 == 1) sum -= X[N - 1];
			max = Math.max(max, sum);
		}
		System.out.print(max);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}