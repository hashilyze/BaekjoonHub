import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 10;
	// variables
	static int N;
	static int[] mat = new int[SIZE];
	static int[][][] dp = new int[SIZE][0x01 << SIZE][0x01 << SIZE];
	
	/*
	 * 전략 y축 방향으로 스위핑하면서 경우의 수를 센다.
	 * (y=i)일떄 가능한 모든 비숍을 배치하는 조합에서, (y=i+1)일때 비숍을 배치하는 모든 조합을 센다. 
	 */
	static int eachSubset(int y, int left, int right) {
		if(y == N) {
			return 0;
		}
		if(dp[y][left][right] >= 0) return dp[y][left][right];
		
		int max = 0;
		int row = mat[y] | left | right; // off된 비트에만 비숍을 설치할 수 있다.
		for(int bits = 0x00, limit = 0x01 << N; bits < limit; ++bits) {
			if((row & bits) != 0) continue; // on인 비트에 비숍을 설치할 수 없다.
			// 위에 설치된 비숍들로 인해 설치 불가한 영역을 전파한다.
			max = Math.max(
					max, 
					eachSubset(y+1, (limit - 1) & (left | bits) << 1, (right | bits) >> 1) + Integer.bitCount(bits)
				);
		}
		return dp[y][left][right] = max;
	}
	
	public static void main(String[] args) throws IOException {
		// Inputs
		N = readInt();
		for(int y = 0; y < N; ++y) {
			int row = 0;
			for(int x = 0; x < N; ++x) row = row << 1 | readInt()^1; 
			mat[y] = row;
		}
		// Output
		for(int y = 0; y < N; ++y) {
			for(int bits = 0, limit = 0x01 << N; bits < limit; ++bits) {
				Arrays.fill(dp[y][bits], -1);
			}
		}
		System.out.println(eachSubset(0, 0x00, 0x00));
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}