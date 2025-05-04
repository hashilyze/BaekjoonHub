import java.io.*;
import java.util.*;


public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int SIZE = 100_000;
	// variables
	static int[] f = new int[SIZE + 1];
	static int[] g = new int[SIZE + 1];
	static boolean[] isVisited = new boolean[SIZE + 1];
	
	
	static int combine(int a, int b) {
		int shift = 10;
		while(b >= shift) shift *= 10;
		return a * shift + b;
	}
	
	static void initF() {
		for(int N = 1; N <= SIZE; ++N) {
			int x = N;
			int a = 0, m = 1;
			
			while(x > 0) {
				int digit = x % 10;
				
				a += digit;
				m *= digit;
				
				x /= 10;
			}
			f[N] = combine(a, m);
		}
	}
	
	static int getG(int N) {
		if(g[N] >= -1) return g[N];
		
		if(N == f[N]) return g[N] = 1;
		if(f[N] > SIZE) return g[N] = -1;
		if(isVisited[N]) return g[N] = 0;
		
		isVisited[N] = true;
		int ans = getG(f[N]);
		isVisited[N] = false;
		return ans;
	}
	
	
	public static void main(String[] args) throws IOException {
		initF();
		Arrays.fill(g, -2);
		
		int L = readInt(), R = readInt();
		int sum = 0;
		for(int i = L; i <= R; ++i) {
			sum += getG(i);
		}
		System.out.print(sum);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}